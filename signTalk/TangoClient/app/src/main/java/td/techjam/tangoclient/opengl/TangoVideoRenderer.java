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
package td.techjam.tangoclient.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Environment;

import td.techjam.tangoclient.Utils;
import td.techjam.tangoclient.training.TangoFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * A simple OpenGL renderer that renders the Tango RGB camera texture on a full-screen background.
 */
public class TangoVideoRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = TangoVideoRenderer.class.getSimpleName();

    // Dimensions and color for the rectangle (i.e. the capture area)
    private int x = 0;
    private int y = 0;
    private int width = 400;
    private int height = 400;
    float color[] = { 1.0f, 0.0f, 0.0f, 1.0f };   // yellow

    private OpenGlSquare mRectangle;
    private OpenGlCameraPreview mOpenGlCameraPreview;
    private boolean mProjectionMatrixConfigured;

    /**
     * A small callback to allow the caller to introduce application-specific code to be executed
     * in the OpenGL thread.
     */
    public interface RenderCallback {
        void preRender();
        void postRender();
    }

    private RenderCallback mRenderCallback;
    private Context context;

    private float[] mViewMatrix = new float[16];
    private float[] mProjectionMatrix = new float[16];
    private float[] mVPMatrix = new float[16];

    public TangoVideoRenderer(Context context, RenderCallback callback) {
        this.context = context;
        mRenderCallback = callback;

        mOpenGlCameraPreview = new OpenGlCameraPreview();
    }

    public void updateColorCameraTextureUv(int rotation) {
        mOpenGlCameraPreview.updateTextureUv(rotation);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // Enable depth test to discard fragments that are behind another fragment.
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        // Enable face culling to discard back-facing triangles.
//        GLES20.glEnable(GLES20.GL_CULL_FACE);
//        GLES20.glCullFace(GLES20.GL_BACK);
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glDepthMask(true);
        mOpenGlCameraPreview.setUpProgramAndBuffers();

        mRectangle = new OpenGlSquare(x, y, width, height, color);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        setProjectionMatrix(width, height);
        mProjectionMatrixConfigured = false;
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Call application-specific code that needs to run on the OpenGL thread.
        mRenderCallback.preRender();

        // Don't write depth buffer because we want to draw the camera as background.
        GLES20.glDepthMask(false);
        mOpenGlCameraPreview.drawAsBackground();
        // Enable depth buffer again for AR.
        GLES20.glDepthMask(true);

//        updateVPMatrix();

//        GLES20.glEnable(GLES20.GL_BLEND);
//        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

//        mRectangle.draw(mVPMatrix);

        //        byte[] rgbData = savePixels(0, 0, 25, 25);
        mRenderCallback.postRender();

        //        Utils.LogD("malik", String.format("Read %d pixels", (width * height)));
        //        int color = bitmap.getPixel(0, 0);
        //        int a = (color >> 24) & 0xff; // or color >>> 24
        //        int r = (color >> 16) & 0xff;
        //        int g = (color >> 8) & 0xff;
        //        int b = (color) & 0xff;
        //        Utils.LogD("malik", String.format("r:%d, g:%d, b:%d, a:%d", r, g, b, a));
        //
        //        storeImage(bitmap);

    }

    /**
     * Set the Projection matrix matching the Tango RGB camera in order to be able to do
     * augmented reality.
     */
    public void setProjectionMatrix(float[] matrixFloats, float nearPlane, float farPlane) {
        mProjectionMatrix = matrixFloats;
        mProjectionMatrixConfigured = true;
    }

    /**
     * Update the View matrix matching the pose of the Tango RGB camera.
     *
     * @param ssTcamera The transform from RGB camera to Start of Service.
     */
    public void updateViewMatrix(float[] ssTcamera) {
        float[] viewMatrix = new float[16];
        Matrix.invertM(viewMatrix, 0, ssTcamera, 0);
        mViewMatrix = viewMatrix;
    }

    public boolean isProjectionMatrixConfigured() {
        return mProjectionMatrixConfigured;
    }

    /**
     * Composes the view and projection matrices into a single VP matrix.
     */
    private void updateVPMatrix() {
        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -5,
            0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        // (i.e. multiply mProjectionMatrix by mViewMatrix and store it in mMVPMatrix)
        Matrix.multiplyMM(mVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    }

    private void setProjectionMatrix(int width, int height) {
        float ratio = (float)width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        // Transforms shapes to adjust according to the device size (because by default OpenGL assumes all devices
        // are a perfect square)
        // NOTE: must also apply a camera view transformation in {@code onDrawFrame} in order for anything to show up
        // on screen
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public int getTextureId() {
        return mOpenGlCameraPreview.getTextureId();
    }

    public void readPixelData(TangoFragment.OnFragmentInteractionListener listener) {
        //        byte pixelData[] = new byte[w * h * 4];
        //        int bitmapData[] = new int[w * h];
        //        ByteBuffer byteBuffer = ByteBuffer.wrap(pixelData);
        //        byteBuffer.position(0);

        byte pixelData[] = new byte[width * height * 4];
        ByteBuffer byteBuffer = ByteBuffer.wrap(pixelData);
        byteBuffer.position(0);

        GLES20.glReadPixels(x, y, width, height, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, byteBuffer);

        Utils.LogD(TAG,
            String.format("r:%d g:%d b:%d a:%d", pixelData[0], pixelData[1], pixelData[2], pixelData[3]));

        int colorData[] = new int[width * height];
        int colorDataCounter = 0;

        for (int i = 0; i < pixelData.length; i++) {
            if ((i + 1) % 4 == 0) {
                byte r = pixelData[i - 3];
                byte g = pixelData[i - 2];
                byte b = pixelData[i - 1];
                byte a = pixelData[i];

                // Encode RGBA to sRGBA (single int) based on this link
                // https://developer.android.com/reference/android/graphics/Color.html
                int color = (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
                colorData[colorDataCounter++] = color;
            }
        }

        listener.onFrameDataReceived(colorData);

        //        Utils.LogD(TAG,
        //            String.format("sRGB:%d | r:%d g:%d b:%d a:%d", bitmapData[0], pixelData[0], pixelData[1],
        // pixelData[2],
        //                pixelData[3]));

        //        Utils.LogD(TAG, String.format("r:%d g:%d b:%d a:%d", pixelData[0], pixelData[1], pixelData[2],
        // pixelData[3]));

        //        return Bitmap.createBitmap(bitmapData, w, h, Bitmap.Config.ARGB_8888);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
