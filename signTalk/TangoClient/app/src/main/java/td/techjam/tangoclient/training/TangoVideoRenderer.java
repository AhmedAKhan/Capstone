/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package td.techjam.tangoclient.training;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Environment;

import com.projecttango.tangosupport.TangoSupport;

import td.techjam.tangoclient.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * A simple OpenGL renderer that renders the Tango RGB camera texture on a full-screen background.
 */
public class TangoVideoRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = TangoVideoRenderer.class.getSimpleName();

    private final String vss =
        "attribute vec2 vPosition;\n" +
            "attribute vec2 vTexCoord;\n" +
            "varying vec2 texCoord;\n" +
            "void main() {\n" +
            "  texCoord = vTexCoord;\n" +
            "  gl_Position = vec4(vPosition.x, vPosition.y, 0.0, 1.0);\n" +
            "}";

    private final String fss =
        "#extension GL_OES_EGL_image_external : require\n" +
            "precision mediump float;\n" +
            "uniform samplerExternalOES sTexture;\n" +
            "varying vec2 texCoord;\n" +
            "void main() {\n" +
            "  gl_FragColor = texture2D(sTexture,texCoord);\n" +
            "}";

    private final float[] textureCoords0 =
        new float[] { 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f };

    private int width;
    private int height;

    private int threadNum;
    private Semaphore lock = new Semaphore(1);

    /**
     * A small callback to allow the caller to introduce application-specific code to be executed
     * in the OpenGL thread.
     */
    public interface RenderCallback {
        void preRender();
        void postRender(int width, int height);
    }

    private FloatBuffer mVertex;
    private FloatBuffer mTexCoord;
    private ShortBuffer mIndices;
    private int[] mVbos;
    private int[] mTextures = new int[1];
    private int mProgram;
    private RenderCallback mRenderCallback;
    private Context context;

    public TangoVideoRenderer(Context context, RenderCallback callback) {
        this.context = context;
        mRenderCallback = callback;
        mTextures[0] = 0;
        // Vertex positions.
        float[] vtmp = { 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f };
        // Vertex texture coords.
        float[] ttmp = { 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        // Indices.
        short[] itmp = { 0, 1, 2, 3 };
        mVertex = ByteBuffer.allocateDirect(vtmp.length * Float.SIZE / 8).order(
            ByteOrder.nativeOrder()).asFloatBuffer();
        mVertex.put(vtmp);
        mVertex.position(0);
        mTexCoord = ByteBuffer.allocateDirect(ttmp.length * Float.SIZE / 8).order(
            ByteOrder.nativeOrder()).asFloatBuffer();
        mTexCoord.put(ttmp);
        mTexCoord.position(0);
        mIndices = ByteBuffer.allocateDirect(itmp.length * Short.SIZE / 8).order(
            ByteOrder.nativeOrder()).asShortBuffer();
        mIndices.put(itmp);
        mIndices.position(0);
    }

    public void updateColorCameraTextureUv(int rotation) {
        float[] textureCoords =
            TangoSupport.getVideoOverlayUVBasedOnDisplayRotation(textureCoords0, rotation);
        setTextureCoords(textureCoords);
    }

    private void setTextureCoords(float[] textureCoords) {
        mTexCoord.put(textureCoords);
        mTexCoord.position(0);
        if (mVbos != null) {
            // Bind to texcoord buffer.
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVbos[1]);
            // Populate it.
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, 4 * 2 * Float
                .SIZE / 8, mTexCoord, GLES20.GL_STATIC_DRAW); // texcoord of floats.
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        createTextures();
        createCameraVbos();
        GLES20.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);
        mProgram = getProgram(vss, fss);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        this.width = width;
        this.height = height;
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Call application-specific code that needs to run on the OpenGL thread.
        mRenderCallback.preRender();

        GLES20.glUseProgram(mProgram);

        // Don't write depth buffer because we want to draw the camera as background.
        GLES20.glDepthMask(false);

        int ph = GLES20.glGetAttribLocation(mProgram, "vPosition");
        int tch = GLES20.glGetAttribLocation(mProgram, "vTexCoord");
        int th = GLES20.glGetUniformLocation(mProgram, "sTexture");

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextures[0]);
        GLES20.glUniform1i(th, 0);

        GLES20.glEnableVertexAttribArray(ph);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVbos[0]);
        GLES20.glVertexAttribPointer(ph, 2, GLES20.GL_FLOAT, false, 4 * 2, 0);

        GLES20.glEnableVertexAttribArray(tch);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVbos[1]);
        GLES20.glVertexAttribPointer(tch, 2, GLES20.GL_FLOAT, false, 4 * 2, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mVbos[2]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, 4, GLES20.GL_UNSIGNED_SHORT, 0);

//        byte[] rgbData = savePixels(0, 0, 25, 25);
        mRenderCallback.postRender(width, height);

//        Utils.LogD("malik", String.format("Read %d pixels", (width * height)));
//        int color = bitmap.getPixel(0, 0);
//        int a = (color >> 24) & 0xff; // or color >>> 24
//        int r = (color >> 16) & 0xff;
//        int g = (color >> 8) & 0xff;
//        int b = (color) & 0xff;
//        Utils.LogD("malik", String.format("r:%d, g:%d, b:%d, a:%d", r, g, b, a));
//
//        storeImage(bitmap);

        // Unbind.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        // Enable depth write again for any additional rendering on top of the camera surface.
        GLES20.glDepthMask(true);
    }

    private void createTextures() {
        mTextures = new int[1];
        GLES20.glGenTextures(1, mTextures, 0);
        GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTextures[0]);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
            GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
            GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
            GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES,
            GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
    }

    /**
     * Creates and populates vertex buffer objects for rendering the camera.
     */
    private void createCameraVbos() {
        mVbos = new int[3];
        // Generate three buffers: vertex buffer, texture buffer and index buffer.
        GLES20.glGenBuffers(3, mVbos, 0);
        // Bind to vertex buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVbos[0]);
        // Populate it.
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mVertex.capacity() * Float.SIZE / 8,
            mVertex, GLES20.GL_STATIC_DRAW); // 4 2D vertex of floats.

        // Bind to texture buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVbos[1]);
        // Populate it.
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mTexCoord.capacity() * Float.SIZE / 8,
            mTexCoord, GLES20.GL_STATIC_DRAW); // 4 2D texture coords of floats.

        // Bind to indices buffer.
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mVbos[2]);
        // Populate it.
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, mIndices.capacity() * Short.SIZE / 8,
            mIndices, GLES20.GL_STATIC_DRAW); // 4 short indices.

        // Unbind buffer.
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    private int getProgram(String vShaderSrc, String fShaderSrc) {
        int program = GLES20.glCreateProgram();
        if (program == 0) {
            return 0;
        }
        int vShader = loadShader(GLES20.GL_VERTEX_SHADER, vShaderSrc);
        int fShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fShaderSrc);
        GLES20.glAttachShader(program, vShader);
        GLES20.glAttachShader(program, fShader);
        GLES20.glLinkProgram(program);
        int[] linked = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linked, 0);
        if (linked[0] == 0) {
            Utils.LogE(TAG, "Could not link program");
            Utils.LogV(TAG, "Could not link program:" +
                GLES20.glGetProgramInfoLog(program));
            GLES20.glDeleteProgram(program);
            return 0;
        }
        return program;
    }

    private int loadShader(int type, String shaderSrc) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderSrc);
        GLES20.glCompileShader(shader);
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Utils.LogE(TAG, "Could not compile shader");
            Utils.LogV(TAG, "Could not compile shader:" +
                GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            return 0;
        }
        return shader;
    }

    public int getTextureId() {
        return mTextures[0];
    }

    public void readPixelData(final int x, final int y, final int w, final int h,
        final TangoFragment.OnFragmentInteractionListener listener) {
//        final byte pixelData[] = new byte[w * h * 4];
////        int bitmapData[] = new int[w * h];
//        final ByteBuffer byteBuffer = ByteBuffer.wrap(pixelData);
//        byteBuffer.position(0);

        final byte pixelData[] = new byte[w * h * 4];
        ByteBuffer byteBuffer = ByteBuffer.wrap(pixelData);
        byteBuffer.position(0);

        GLES20.glReadPixels(x, y, w, h, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, byteBuffer);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                byte pixelData[] = new byte[w * h * 4];
//                ByteBuffer byteBuffer = ByteBuffer.wrap(pixelData);
//                byteBuffer.position(0);
//
//                GLES20.glReadPixels(x, y, w, h, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, byteBuffer);

                if (lock.tryAcquire()) {
                    threadNum++;
                    lock.release();
                }

                Utils.LogD(TAG, String.format("Thread %d finished", threadNum));
                Utils.LogD(TAG,
                    String.format("r:%d g:%d b:%d a:%d", pixelData[0], pixelData[1], pixelData[2], pixelData[3]));
                listener.onPixelDataReceived(pixelData);
            }
        };
        new Thread(runnable).start();

//        int bitmapCounter = 0;
//        for (int i = 0; i < pixelData.length; i++) {
//            if ((i + 1) % 4 == 0) {
//                byte r = pixelData[i - 3];
//                byte g = pixelData[i - 2];
//                byte b = pixelData[i - 1];
//                byte a = pixelData[i];
//
//                // Encode RGBA to sRGBA (single int) based on this link
//                // https://developer.android.com/reference/android/graphics/Color.html
//                int color = (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
//                bitmapData[bitmapCounter++] = color;
//            }
//        }

//        Utils.LogD(TAG,
//            String.format("sRGB:%d | r:%d g:%d b:%d a:%d", bitmapData[0], pixelData[0], pixelData[1], pixelData[2],
//                pixelData[3]));

//        Utils.LogD(TAG, String.format("r:%d g:%d b:%d a:%d", pixelData[0], pixelData[1], pixelData[2], pixelData[3]));

//        return Bitmap.createBitmap(bitmapData, w, h, Bitmap.Config.ARGB_8888);
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Utils.LogD(TAG,
                "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            Utils.LogD(TAG, String.format("Saving file in %s", pictureFile.getAbsolutePath()));
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Utils.LogD(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Utils.LogD(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    /** Create a File for saving an image or video */
    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
            + "/Android/data/"
            + context.getPackageName()
            + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyy-mm-dd_hh:mm:ss").format(new Date());
        File mediaFile;
        String mImageName = "MI_" + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}
