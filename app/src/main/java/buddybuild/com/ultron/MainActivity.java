package buddybuild.com.ultron;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import buddybuild.com.ultron.controller.AppsController;
import buddybuild.com.ultron.model.App;
import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements AppsRecyclerViewAdapter.OnAppClickListener {

    public static final String APP_ID = "com.buddybuild.Ultron.APP_ID";
    public static final String APP_NAME = "com.buddybuild.Ultron.APP_NAME";

    @Inject
    AppsController appsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView list = (RecyclerView) findViewById(R.id.apps_list);

        final MainActivity self = this;
        appsController.list().enqueue(new Callback<List<App>>() {
            @Override
            public void onResponse(Call<List<App>> call, Response<List<App>> response) {
                AppsRecyclerViewAdapter adapter = new AppsRecyclerViewAdapter(response.body(), self);
                RecyclerView list = (RecyclerView) findViewById(R.id.apps_list);
                list.setAdapter(adapter);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<App>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAppClick(App app) {
        System.out.println("Did select app id = " + app.getId());

        Intent intent = new Intent(this, BuildsActivity.class);
        intent.putExtra(APP_ID, app.getId());
        intent.putExtra(APP_NAME, app.getName());
        startActivity(intent);
    }
}
