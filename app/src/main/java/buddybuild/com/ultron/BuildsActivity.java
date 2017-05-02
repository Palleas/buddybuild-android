package buddybuild.com.ultron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import buddybuild.com.ultron.controller.AppsController;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BuildsActivity extends AppCompatActivity {

    private String appId;

    @BindView(R.id.builds_list) RecyclerView list;

    @Inject
    AppsController appsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_builds);

        ButterKnife.bind(this);

        setTitle(getIntent().getStringExtra(MainActivity.APP_NAME));

        appId = getIntent().getStringExtra(MainActivity.APP_ID);

        appsController
                .builds(appId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(builds -> {
                    BuildsRecyclerViewAdapter adapter = new BuildsRecyclerViewAdapter(builds);
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
