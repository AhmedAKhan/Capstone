package td.techjam.tangoclient;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlaskApi {

    @GET("test")
    Call<String> getTest();
}
