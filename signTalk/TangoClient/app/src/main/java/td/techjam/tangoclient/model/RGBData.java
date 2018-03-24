package td.techjam.tangoclient.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RGBData implements Parcelable {

    public int width;
    public int height;
    public int numFrames;
    public byte[][] frames;

    public RGBData(int width, int height, int numFrames, byte[][] frames) {
        this.width = width;
        this.height = height;
        this.numFrames = numFrames;
        this.frames = frames;
    }

    private RGBData(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.numFrames = in.readInt();
        read2DArray(in);
    }

    public static final Creator<RGBData> CREATOR = new Creator<RGBData>() {
        @Override
        public RGBData createFromParcel(Parcel in) {
            return new RGBData(in);
        }

        @Override
        public RGBData[] newArray(int size) {
            return new RGBData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeInt(numFrames);
        write2DArray(parcel);
    }

    private void write2DArray(Parcel parcel) {
        int I = frames.length;
        int J = I > 0 ? frames[0].length : 0;

        parcel.writeInt(I);
        parcel.writeInt(J);

        for (int i = 0; i < I; i++) {
            for (int j = 0; j < J; j++) {
                parcel.writeInt(frames[i][j]);
            }
        }
    }

    private void read2DArray(Parcel in) {
        int I = in.readInt();
        int J = in.readInt();

        for (int i = 0; i < I; i++) {
            for (int j = 0; j < J; j++) {
                frames[i][j] = in.readByte();
            }
        }
    }
}
