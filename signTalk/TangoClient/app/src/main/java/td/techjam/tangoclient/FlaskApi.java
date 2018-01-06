package td.techjam.tangoclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FlaskApi {

    @GET("test")
    Call<String> getTest();
    @POST("tango/save")
    Call<GenericResponse> postSave(@Body SaveRequest saveRequest);
}
