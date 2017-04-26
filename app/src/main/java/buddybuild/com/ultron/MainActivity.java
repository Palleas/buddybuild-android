package buddybuild.com.ultron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import buddybuild.com.ultron.controller.AppsController;
import buddybuild.com.ultron.model.App;
import buddybuild.com.ultron.model.Buddybuild;
import dagger.android.AndroidInjection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    AppsController appsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        appsController.list().enqueue(new Callback<List<App>>() {
            @Override
            public void onResponse(Call<List<App>> call, Response<List<App>> response) {
                AppsRecyclerViewAdapter adapter = new AppsRecyclerViewAdapter(response.body());
                RecyclerView list = (RecyclerView) findViewById(R.id.apps_list);
                list.setAdapter(adapter);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<App>> call, Throwable t) {

            }
        });
    }
}
