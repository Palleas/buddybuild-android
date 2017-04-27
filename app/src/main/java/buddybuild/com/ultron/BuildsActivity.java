package buddybuild.com.ultron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.reactivestreams.Subscriber;

import java.util.List;

import javax.inject.Inject;

import buddybuild.com.ultron.controller.AppsController;
import buddybuild.com.ultron.model.Build;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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

        appsController
                .builds(appId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(builds -> {
                    BuildsRecyclerViewAdapter adapter = new BuildsRecyclerViewAdapter(builds);
                    RecyclerView list = (RecyclerView) findViewById(R.id.builds_list);
                    list.setAdapter(adapter);
                    list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
    }

    public void onTriggerClick(View view) {
        appsController.trigger(appId)
                .subscribeOn(Schedulers.io())
                .subscribe(build -> System.out.print("Response = " + build.getBuildStatus()));
    }
}
