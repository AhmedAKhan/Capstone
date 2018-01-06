package td.techjam.tangoclient;

import android.os.Parcel;
import android.os.Parcelable;

public class Resolution implements Parcelable {

    int width;
    int height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private Resolution(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<Resolution> CREATOR = new Creator<Resolution>() {
        @Override
        public Resolution createFromParcel(Parcel in) {
            return new Resolution(in);
        }

        @Override
        public Resolution[] newArray(int size) {
            return new Resolution[size];
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
    }
}
