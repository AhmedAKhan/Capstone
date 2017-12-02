package td.techjam.tangoclient.training;

import android.support.annotation.ColorRes;

public interface TrainingView {
    void updateOneButton(String text, @ColorRes int color);
    void updateTwoButton(String textLeft, String textRight, @ColorRes int colorLeft, @ColorRes int colorRight);
    void startRecording();
    void stopRecording();
    void saveRecording();
    void updateRecordingStatus(String status);
    void resetRecordingProgress();
}
