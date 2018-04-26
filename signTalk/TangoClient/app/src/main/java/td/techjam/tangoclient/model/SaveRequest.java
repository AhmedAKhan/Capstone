package td.techjam.tangoclient.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveRequest implements Parcelable {

    public String letter;
    public RGBData rgb;

    public SaveRequest(String letter, RGBData rgb) {
        this.letter = letter;
        this.rgb = rgb;
    }

    private SaveRequest(Parcel in) {
        this.letter = in.readString();
        this.rgb = in.readParcelable(getClass().getClassLoader());
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
        parcel.writeString(letter);
        parcel.writeParcelable(rgb, 0);
    }

}
