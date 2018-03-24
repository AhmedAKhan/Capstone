package td.techjam.tangoclient.training;

import android.support.annotation.ColorRes;

import td.techjam.tangoclient.R;
import td.techjam.tangoclient.model.RGBData;

import java.util.ArrayList;

public class TrainingPresenter {

    private enum ButtonState {
        START, STOP, SAVE_RESET
    }

    private ButtonState buttonState = ButtonState.START; // default state is START
    private TrainingView view;
    private boolean recording = false;

    private int width;
    private int height;
    private int numFrames = 0;
    private ArrayList<byte[]> rgbFrames = new ArrayList<>();

    TrainingPresenter(TrainingView view) {
        this.view = view;
        view.updateOneButton(buttonState.toString(), getLeftButtonColor());
    }

    public boolean isRecording() {
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

    void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void onFrameDataReceived(byte[] frame) {
        numFrames++;
        rgbFrames.add(frame);
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
        byte[][] frames = (byte[][]) rgbFrames.toArray();
        RGBData rgbData = new RGBData(width, height, numFrames, frames);
        view.saveRecording(rgbData);
        resetStoredData();
    }

    private void resetClicked() {
        buttonState = ButtonState.START;
        view.updateOneButton("START", getLeftButtonColor());
        resetRecordingStatus();
        resetStoredData();
    }

    private void resetStoredData() {
        numFrames = 0;
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
}
