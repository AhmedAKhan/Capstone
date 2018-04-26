package td.techjam.tangoclient.training;

import android.support.annotation.ColorRes;

import td.techjam.tangoclient.R;
import td.techjam.tangoclient.Utils;
import td.techjam.tangoclient.model.RGBData;
import td.techjam.tangoclient.model.SaveRequest;

import java.util.ArrayList;

public class TrainingPresenter implements TangoFragment.OnFragmentInteractionListener {
    private static final String TAG = TrainingPresenter.class.getSimpleName();

    private enum ButtonState {
        START, STOP, SAVE_RESET
    }

    private ButtonState buttonState = ButtonState.START; // default state is START
    private TrainingView view;
    private boolean recording = false;

    private int width;
    private int height;
    private int totalFrames = 0;
    private int recordedFrames = 0;
    private ArrayList<int[]> rgbFrames = new ArrayList<>();
    private String letter;

    TrainingPresenter(TrainingView view, String letter) {
        this.view = view;
        this.letter = letter;
        view.updateOneButton(buttonState.toString(), getLeftButtonColor());
    }

    boolean isRecording() {
        return recording;
    }

    void singleButtonClicked() {
        switch (buttonState) {
            case START:
                startClicked();
                break;
            case STOP:
                stopClicked();
                break;
            default:
                break;
        }
    }

    void dualButtonClicked(boolean left) {
        if (left) {
            saveClicked();
        } else {
            resetClicked();
        }
    }

    void onRecordingFinished() {
        buttonState = ButtonState.SAVE_RESET;
        view.updateTwoButton("Save", "Reset", getLeftButtonColor(), getRightButtonColor());
        view.stopRecording();
        updateRecordingStatus("Recording Completed");
        recording = false;
    }

    @Override
    public void onDimensionDataReceived(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Called from the OpenGL thread
     */
    @Override
    public void onFrameDataReceived(int[] frame) {
        // Record on 1/3rd of the total frames
        if (totalFrames % 3 == 0){
            rgbFrames.add(frame);
            recordedFrames++;
        }
        totalFrames++;

        int color = frame[0];
        int a = (color >> 24) & 0xff; // or color >>> 24
        int r = (color >> 16) & 0xff;
        int g = (color >> 8) & 0xff;
        int b = (color) & 0xff;

        Utils.LogD(TAG, String.format("RGB data received for %d frames", totalFrames));
        Utils.LogD(TAG, String.format("r:%d g:%d b:%d a:%d", r, g, b, a));
    }

    private void startClicked() {
        buttonState = ButtonState.STOP;
        view.updateOneButton("STOP", getLeftButtonColor());
        view.startRecording();
        updateRecordingStatus("Recording in progress...");
        recording = true;
    }

    private void stopClicked() {
        buttonState = ButtonState.START;
        view.updateOneButton("START", getLeftButtonColor());
        view.stopRecording();
        updateRecordingStatus("Recording Cancelled");
        recording = false;
    }

    private void saveClicked() {
//        view.showProgressBar();
        Utils.LogD("malik", "save clicked");
        int[][] frames = getFramesAsArray();
        RGBData rgbData = new RGBData(width, height, recordedFrames, frames);
        Utils.LogD("malik", "extracted " + frames.length + " frames");
//        view.hideProgressBar();

        view.saveRecording(createSaveRequest(rgbData));
        resetStoredData();
    }

    private void resetClicked() {
        buttonState = ButtonState.START;
        view.updateOneButton("START", getLeftButtonColor());
        resetRecordingStatus();
        resetStoredData();
    }

    private void resetStoredData() {
        totalFrames = 0;
        recordedFrames = 0;
        rgbFrames = new ArrayList<>();
    }

    private @ColorRes int getLeftButtonColor() {
        switch (buttonState) {
            case START:
                return R.color.colorStart;
            case STOP:
                return R.color.colorStop;
            case SAVE_RESET:
                return R.color.colorSave;
            default:
                return 0;
        }
    }

    private @ColorRes int getRightButtonColor() {
        return buttonState == ButtonState.SAVE_RESET ? R.color.colorReset : 0;
    }

    private void updateRecordingStatus(String status) {
        view.updateRecordingStatus(status);
    }

    private void resetRecordingStatus() {
        view.updateRecordingStatus("");
    }

    private int[][] getFramesAsArray() {
        int[][] frames = new int[rgbFrames.size()][rgbFrames.get(0).length];
        for (int i = 0; i < frames.length; i++) {
            for (int j = 0; j < frames[0].length; j++) {
                frames[i][j] = rgbFrames.get(i)[j];
            }
        }
        return frames;
    }

    private SaveRequest createSaveRequest(RGBData rgbData) {
        return new SaveRequest(letter, rgbData);
    }
}
