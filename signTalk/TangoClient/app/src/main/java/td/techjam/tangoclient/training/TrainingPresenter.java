package td.techjam.tangoclient.training;

import android.support.annotation.ColorRes;

import td.techjam.tangoclient.R;

public class TrainingPresenter {

    enum ButtonState {
        START, STOP, SAVE_RESET
    }

    private ButtonState buttonState = ButtonState.START; // default state is START
    private TrainingView view;

    TrainingPresenter(TrainingView view) {
        this.view = view;
        view.updateOneButton(buttonState.toString(), getLeftButtonColor());
    }

    ButtonState getButtonState() {
        return buttonState;
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

        view.updateOneButton(buttonState.toString(), getLeftButtonColor());
    }

    void onRecordingFinished() {
        buttonState = ButtonState.SAVE_RESET;
        view.updateTwoButton("Save", "Reset", getLeftButtonColor(), getRightButtonColor());
    }

    private void startClicked() {
        buttonState = ButtonState.STOP;
        view.startTimer();
    }

    private void stopClicked() {
        buttonState = ButtonState.START;
        view.stopTimer();
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
}
