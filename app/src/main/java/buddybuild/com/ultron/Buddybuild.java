package buddybuild.com.ultron;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Buddybuild {

    @GET("apps/{app_id}/build/latest")
    Call<Build> latestBuild(@Header("Authorization") String authorization, @Path("app_id") String appId, @Query("branch") String branch);

    @FormUrlEncoded
    @POST("apps/{app_id}/build")
    Call<Build> trigger(@Header("Authorization") String authorization, @Path("app_id") String appId, @Field("branch") String branch);

}
