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
import butterknife.BindView;
import dagger.Binds;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements AppsRecyclerViewAdapter.OnAppClickListener {

    public static final String APP_ID = "com.buddybuild.Ultron.APP_ID";
    public static final String APP_NAME = "com.buddybuild.Ultron.APP_NAME";

    @Inject
    AppsController appsController;

    @BindView(R.id.apps_list) RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView list = (RecyclerView) findViewById(R.id.apps_list);

        final MainActivity self = this;
        appsController.list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apps -> {
                    AppsRecyclerViewAdapter adapter = new AppsRecyclerViewAdapter(apps, self);
                    list.setAdapter(adapter);
                    list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                });
    }

    @Override
    public void onAppClick(App app) {
        Intent intent = new Intent(this, BuildsActivity.class);
        intent.putExtra(APP_ID, app.getId());
        intent.putExtra(APP_NAME, app.getName());
        startActivity(intent);
    }
}
