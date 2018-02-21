package td.techjam.tangoclient.training;

import android.support.annotation.ColorRes;

import td.techjam.tangoclient.R;

public class TrainingPresenter {

    private enum ButtonState {
        START, STOP, SAVE_RESET
    }

    private ButtonState buttonState = ButtonState.START; // default state is START
    private TrainingView view;

    TrainingPresenter(TrainingView view) {
        this.view = view;
        view.updateOneButton(buttonState.toString(), getLeftButtonColor());
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
        updateRecordingStatus("Recording Completed");
        view.updateTwoButton("Save", "Reset", getLeftButtonColor(), getRightButtonColor());
        view.stopRecording();
    }

    private void startClicked() {
        buttonState = ButtonState.STOP;
        view.updateOneButton("STOP", getLeftButtonColor());
        view.startRecording();
        updateRecordingStatus("Recording in progress...");
    }

    private void stopClicked() {
        buttonState = ButtonState.START;
        view.updateOneButton("START", getLeftButtonColor());
        view.stopRecording();
        updateRecordingStatus("Recording Cancelled");
    }

    private void saveClicked() {
        view.saveRecording();
    }

    private void resetClicked() {
        buttonState = ButtonState.START;
        view.updateOneButton("START", getLeftButtonColor());
        resetRecordingStatus();
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
