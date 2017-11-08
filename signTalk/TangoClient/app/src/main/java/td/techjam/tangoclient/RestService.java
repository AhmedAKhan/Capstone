package td.techjam.tangoclient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    // 10.0.2.2 is used for emulator to connect to localhost
    private static final String BASE_URL = "http://10.0.2.2:4000/";

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
}
