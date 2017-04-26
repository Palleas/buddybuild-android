package buddybuild.com.ultron;

import android.app.Activity;
import android.content.res.Resources;

import java.io.IOException;

import buddybuild.com.ultron.model.Buddybuild;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(subcomponents = {IMainActivitySubcomponent.class})
public abstract class ActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindYourActivityInjectorFactory(IMainActivitySubcomponent.Builder builder);

    @Provides
    static Buddybuild provideBuddybuild() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + BuildConfig.BUDDYBUILD_API_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.buddybuild.com/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(Buddybuild.class);
    }
}
