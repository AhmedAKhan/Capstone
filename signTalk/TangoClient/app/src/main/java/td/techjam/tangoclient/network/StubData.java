package td.techjam.tangoclient.network;

import td.techjam.tangoclient.model.RGBData;
import td.techjam.tangoclient.model.SaveRequest;

public class StubData {

    public static SaveRequest getSaveRequest() {
        String letter = "A";
        int width = 100;
        int height = 100;
        int numFrames = 90;
        byte[][] frames = {{1,2,3,4},{5,6,7,8}};
        RGBData rgbData = new RGBData(width,height,numFrames,frames);

        return new SaveRequest(letter, rgbData);
    }
}
