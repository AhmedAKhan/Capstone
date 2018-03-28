package td.techjam.tangoclient.training;

import android.support.annotation.ColorRes;

import td.techjam.tangoclient.model.RGBData;
import td.techjam.tangoclient.model.SaveRequest;

public interface TrainingView {
    void updateOneButton(String text, @ColorRes int color);
    void updateTwoButton(String textLeft, String textRight, @ColorRes int colorLeft, @ColorRes int colorRight);
    void startRecording();
    void stopRecording();
    void saveRecording(SaveRequest saveRequest);
    void updateRecordingStatus(String status);
}
