package buddybuild.com.ultron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import buddybuild.com.ultron.controller.AppsController;
import buddybuild.com.ultron.model.Build;
import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildsActivity extends AppCompatActivity {

    private String appId;

    @Inject
    AppsController appsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builds);

        setTitle(getIntent().getStringExtra(MainActivity.APP_NAME));

        appId = getIntent().getStringExtra(MainActivity.APP_ID);

        appsController.builds(appId).enqueue(new Callback<List<Build>>() {
            @Override
            public void onResponse(Call<List<Build>> call, Response<List<Build>> response) {
                BuildsRecyclerViewAdapter adapter = new BuildsRecyclerViewAdapter(response.body());
                RecyclerView list = (RecyclerView) findViewById(R.id.builds_list);
                list.setAdapter(adapter);
                list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Build>> call, Throwable t) {

            }
        });
    }

    public void onTriggerClick(View view) {
        appsController.trigger(appId).enqueue(new Callback<Build>() {
            @Override
            public void onResponse(Call<Build> call, Response<Build> response) {
                System.out.print("Response = " + response.body().getBuildStatus());
            }

            @Override
            public void onFailure(Call<Build> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
