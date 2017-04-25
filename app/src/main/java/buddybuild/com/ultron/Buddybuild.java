package buddybuild.com.ultron;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Buddybuild {

    @GET("apps/{app_id}/build/latest?branch={branch}")
    Call<Build> latestBuild(@Header("Authorization") String authorization, @Path("app_id") String id, @Query("branch") String branch);

}
