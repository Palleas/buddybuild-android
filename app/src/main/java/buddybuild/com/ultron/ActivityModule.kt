package buddybuild.com.ultron

import android.app.Activity
import android.content.res.Resources

import java.io.IOException

import buddybuild.com.ultron.model.Buddybuild
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object ActivityModule {

    @Provides
    internal fun provideBuddybuild(): Buddybuild {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                    .header("Authorization", "Bearer " + BuildConfig.BUDDYBUILD_API_KEY)
                    .method(original.method(), original.body())
                    .build()

            chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.buddybuild.com/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build()

        return retrofit.create(Buddybuild::class.java)
    }
}
