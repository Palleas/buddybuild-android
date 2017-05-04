package buddybuild.com.ultron

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import javax.inject.Inject

import buddybuild.com.ultron.controller.AppsController
import butterknife.BindView
import butterknife.ButterKnife
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BuildsActivity : AppCompatActivity() {

    lateinit var appId: String

    @BindView(R.id.builds_list) lateinit var list: RecyclerView

    @Inject lateinit var appsController: AppsController

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_builds)

        ButterKnife.bind(this)

        title = intent.getStringExtra(MainActivity.APP_NAME)

        appId = intent.getStringExtra(MainActivity.APP_ID)

        appsController
                .builds(appId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { builds ->
                    val adapter = BuildsRecyclerViewAdapter(builds)
                    list.adapter = adapter
                    list.layoutManager = LinearLayoutManager(applicationContext)
                }
    }

    fun onTriggerClick(view: View) {
        appsController.trigger(appId)
                .subscribeOn(Schedulers.io())
                .subscribe { build -> print("Response = " + build.buildStatus) }
    }
}
