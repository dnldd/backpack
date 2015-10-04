package dnldd.backpack.contract;

import com.google.gson.JsonObject;

import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

public interface GCMServiceInterface {
    @POST("/gcm/register")
    /* mitigating a retrofit bug by supplying a @Body param, temporary  */
    Observable<JsonObject> register(@Body String body, @Query("token") String token, @Query("id") String id);
}
