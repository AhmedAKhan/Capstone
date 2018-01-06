package td.techjam.tangoclient;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveRequest implements Parcelable {

    int [][][][] frames;
    Resolution resolution;

    public SaveRequest(int[][][][] frames, Resolution resolution) {
        this.frames = frames;
        this.resolution = resolution;
    }

    private SaveRequest(Parcel in) {
        read4DArray(in);
        this.resolution = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<SaveRequest> CREATOR = new Creator<SaveRequest>() {
        @Override
        public SaveRequest createFromParcel(Parcel in) {
            return new SaveRequest(in);
        }

        @Override
        public SaveRequest[] newArray(int size) {
            return new SaveRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        write4DArray(parcel);
        parcel.writeParcelable(resolution, 0);
    }

    private void write4DArray(Parcel parcel) {
        int I = frames.length;
        int J = I > 0 ? frames[0].length : 0;
        int K = J > 0 ? frames[0][0].length : 0;
        int L = K > 0 ? frames[0][0][0].length : 0;

        parcel.writeInt(I);
        parcel.writeInt(J);
        parcel.writeInt(K);
        parcel.writeInt(L);

        for (int i = 0; i < I; i++) {
            for (int j = 0; j < J; j++) {
                for (int k = 0; k < K; k++) {
                    for (int l = 0; l < L; l++) {
                        parcel.writeInt(frames[i][j][k][l]);
                    }
                }
            }
        }
    }

    private void read4DArray(Parcel in) {
        int I = in.readInt();
        int J = in.readInt();
        int K = in.readInt();
        int L = in.readInt();

        for (int i = 0; i < I; i++) {
            for (int j = 0; j < J; j++) {
                for (int k = 0; k < K; k++) {
                    for (int l = 0; l < L; l++) {
                        frames[i][j][k][l] = in.readInt();
                    }
                }
            }
        }
    }
}
