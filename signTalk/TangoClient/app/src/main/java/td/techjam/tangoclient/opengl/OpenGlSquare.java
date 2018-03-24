package td.techjam.tangoclient.opengl;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class OpenGlSquare {
    private static final String TAG = OpenGlSquare.class.getSimpleName();

    // need at least one vertex shader to draw a shape and one fragment shader to color that shape
    private final String vertexShaderCode =
        // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
        "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            // The matrix must be included as a modifier of gl_Position.
            // Note that the uMVPMatrix factor *must be first* in order
            // for the matrix multiplication product to be correct.
            "  gl_Position = uMVPMatrix * vPosition;" +
            "}";

    private final String fragmentShaderCode =
        "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;

    // By default, OpenGL ES assumes a coordinate system where [0,0,0] (X,Y,Z) specifies the center
    // of the GLSurfaceView frame, [1,1,0] is the top right corner of the frame and [-1,-1,0] is
    // bottom left corner of the frame
    static float squareCoords[];

    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

    // Set color with red, green, blue and alpha (opacity) values
    float color[];

    private final int vertexCount;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    private final int program;

    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

    public OpenGlSquare(float topLeftX, float topLeftY, float width, float height, float[] color) {
        // Multiply topLeftX by -1 to transform coordinates from "top-left origin" to "center origin"
//        squareCoords = new float[] {
//            -topLeftX, topLeftY, 0.0f,                   // top left
//            -topLeftX, topLeftY + height, 0.0f,          // bottom left
//            -topLeftX + width, topLeftY + height, 0.0f,  // bottom right
//            -topLeftX + width, topLeftY, 0.0f            // top right
//        };

        float x = 0;
        float y = 0;
        float w = 2;
        float h = 1;

        squareCoords = new float[] {
            x, y, 0.0f,               // top left
            x, y + h, 0.0f,           // bottom left
            x + w, y + h, 0.0f,       // bottom right
            x + w, y, 0.0f            // top right
        };

//        squareCoords = new float[] {
//            -0.5f,  0.5f, 0.0f,   // top left
//            -0.5f, -0.5f, 0.0f,   // bottom left
//            0.5f, -0.5f, 0.0f,   // bottom right
//            0.5f, 0.5f, 0.0f }; // top right

        this.color = color;

        vertexCount = squareCoords.length / COORDS_PER_VERTEX; // i.e. 12/3 = 4

        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 4 bytes per float)
            squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 2 bytes per short)
            drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        // compile vertex and fragment shaders
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        // create empty OpenGL ES Program
        program = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(program, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(program, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(program);
    }

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * @param mvpMatrix
     *     - The Model View Project matrix in which to draw
     *     this shape.
     */
    public void draw(float[] mvpMatrix) {
        // Add program to OpenGL environment
        GLES20.glUseProgram(program);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(program, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the square coordinate data
        GLES20.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false,
            vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(program, "vColor");

        // Set color for drawing the square
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        mMVPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
        checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        checkGlError("glUniformMatrix4fv");

        // Draw the square
        GLES20.glDrawElements(
            GLES20.GL_TRIANGLES, drawOrder.length,
            GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    private int loadShader(int type, String shaderCode) {
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
     * Utility method for debugging OpenGL calls. Provide the name of the call
     * just after making it:
     * <p>
     * <pre>
     * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
     * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
     * <p>
     * If the operation is not successful, the check throws an error.
     *
     * @param glOperation
     *     - Name of the OpenGL call to check.
     */
    private void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}
