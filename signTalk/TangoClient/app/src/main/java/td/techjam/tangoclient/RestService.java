package td.techjam.tangoclient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import td.techjam.tangoclient.model.GenericResponse;
import td.techjam.tangoclient.model.Resolution;
import td.techjam.tangoclient.model.SaveRequest;

public class RestService {

    // 10.0.2.2 is used for emulator to connect to localhost
    private static final String BASE_URL = "http://10.0.2.2:5000/";

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

    public void save(ServiceCallback<GenericResponse> callback) {
        callback.startLoading();

        Call<GenericResponse> call = flaskApi.postSave(createFakeRequest());
        call.enqueue(callback);
    }

    // TODO: Create StubDataManager that creates Request objects directly from JSON
    private SaveRequest createFakeRequest() {
        int[][][][] frames = new int[][][][] {
            {
                {
                    {1,2,3,4},
                    {5,6,7,8}
                },
                {
                    {11,12,13,14},
                    {15,16,17,18}
                }
            },
            {
                {
                    {21,22,23,24},
                    {25,26,27,28}
                },
                {
                    {211,212,213,214},
                    {215,216,217,218}
                }
            },
            {
                {
                    {31,32,33,34},
                    {35,36,37,38}
                },
                {
                    {311,312,313,14},
                    {315,316,317,18}
                }
            },
            {
                {
                    {321,322,323,24},
                    {325,326,327,28}
                },
                {
                    {3211,3212,3213,214},
                    {3215,3216,3217,218}
                }
            }
        };

        return new SaveRequest(frames, new Resolution(10,20));
    }
}
