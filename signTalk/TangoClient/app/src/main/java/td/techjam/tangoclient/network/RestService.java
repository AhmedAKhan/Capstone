package td.techjam.tangoclient.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import td.techjam.tangoclient.model.GenericResponse;
import td.techjam.tangoclient.model.SaveRequest;

public class RestService {

    // 10.0.2.2 is used for emulator to connect to localhost
    private static final String BASE_URL = "https://07b0208d.ngrok.io/";

    private FlaskApi flaskApi;

    public RestService() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        flaskApi = retrofit.create(FlaskApi.class);
    }

    public void getTest(ServiceCallback<String> callback) {
        callback.startLoading();

        Call<String> call = flaskApi.getTest();
        call.enqueue(callback);
    }

    public void save(SaveRequest saveRequest, ServiceCallback<GenericResponse> callback) {
        callback.startLoading();

        Call<GenericResponse> call = flaskApi.postSave(saveRequest);
        call.enqueue(callback);
    }
}
