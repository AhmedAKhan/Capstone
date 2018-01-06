package td.techjam.tangoclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import td.techjam.tangoclient.model.GenericResponse;
import td.techjam.tangoclient.model.SaveRequest;

public interface FlaskApi {

    @GET("test")
    Call<String> getTest();
    @POST("tango/save")
    Call<GenericResponse> postSave(@Body SaveRequest saveRequest);
}
