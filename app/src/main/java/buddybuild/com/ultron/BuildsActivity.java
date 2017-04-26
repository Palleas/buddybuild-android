package buddybuild.com.ultron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import buddybuild.com.ultron.controller.AppsController;
import buddybuild.com.ultron.model.Build;
import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildsActivity extends AppCompatActivity {

    @Inject
    AppsController appsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builds);

        setTitle(getIntent().getStringExtra(MainActivity.APP_NAME));


        appsController.builds(getIntent().getStringExtra(MainActivity.APP_ID)).enqueue(new Callback<List<Build>>() {
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
}
