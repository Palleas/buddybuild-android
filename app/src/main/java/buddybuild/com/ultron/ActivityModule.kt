package buddybuild.com.ultron

import buddybuild.com.ultron.model.Buddybuild
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object ActivityModule {

    @Provides
    @Singleton
    fun provideBuddybuild(): Buddybuild {
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
