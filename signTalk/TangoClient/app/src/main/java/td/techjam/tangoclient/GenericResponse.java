package td.techjam.tangoclient;

import android.os.Parcel;
import android.os.Parcelable;

public class GenericResponse implements Parcelable {

    boolean success;
    String messages;
    int responseCode;

    public GenericResponse(boolean success, String messages, int responseCode) {
        this.success = success;
        this.messages = messages;
        this.responseCode = responseCode;
    }

    private GenericResponse(Parcel in) {
        this.success = Boolean.parseBoolean(in.readString());
        this.messages = in.readString();
        this.responseCode = in.readInt();
    }

    public static final Parcelable.Creator<GenericResponse> CREATOR = new Parcelable.Creator<GenericResponse>() {

        @Override
        public GenericResponse createFromParcel(Parcel parcel) {
            return new GenericResponse(parcel);
        }

        @Override
        public GenericResponse[] newArray(int size) {
            return new GenericResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(Boolean.toString(success));
        parcel.writeString(messages);
        parcel.writeInt(responseCode);
    }
}
