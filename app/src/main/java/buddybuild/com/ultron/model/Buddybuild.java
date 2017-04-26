package buddybuild.com.ultron.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Buddybuild {

    @GET("apps")
    Call<List<App>> apps();

    @GET("apps/{app_id}/builds")
    Call<List<Build>> builds(@Path("app_id") String appId, @Query("limit") int limit);

    @FormUrlEncoded
    @POST("apps/{app_id}/build")
    Call<Build> trigger(@Path("app_id") String appId, @Field("branch") String branch);

}
