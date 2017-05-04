package buddybuild.com.ultron.model

import javax.inject.Singleton

import buddybuild.com.ultron.ActivityModule
import dagger.Component
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Buddybuild {

    @GET("apps")
    fun apps(): Observable<List<App>>

    @GET("apps/{app_id}/builds")
    fun builds(@Path("app_id") appId: String, @Query("limit") limit: Int): Observable<List<Build>>

    @FormUrlEncoded
    @POST("apps/{app_id}/build")
    fun trigger(@Path("app_id") appId: String, @Field("branch") branch: String?): Observable<Build>

}
