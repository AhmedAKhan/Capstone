package td.techjam.tangoclient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    // This URL will have to be changed every time since python server is currently running on localhost
    private static final String BASE_URL = "http://9125174e.ngrok.io/";

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
