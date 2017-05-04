package buddybuild.com.ultron

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import javax.inject.Inject

import buddybuild.com.ultron.controller.AppsController
import buddybuild.com.ultron.model.App
import butterknife.BindView
import butterknife.ButterKnife
import dagger.Binds
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity(), AppsRecyclerViewAdapter.OnAppClickListener {

    @Inject
    internal var appsController: AppsController? = null

    @BindView(R.id.apps_list) internal var list: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.applicationComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        val self = this
        appsController!!.list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { apps ->
                    val adapter = AppsRecyclerViewAdapter(apps, self)
                    list!!.adapter = adapter
                    list!!.layoutManager = LinearLayoutManager(applicationContext)
                }
    }

    override fun onAppClick(app: App) {
        val intent = Intent(this, BuildsActivity::class.java)
        intent.putExtra(APP_ID, app.id)
        intent.putExtra(APP_NAME, app.name)
        startActivity(intent)
    }

    companion object {

        val APP_ID = "com.buddybuild.Ultron.APP_ID"
        val APP_NAME = "com.buddybuild.Ultron.APP_NAME"
    }
}
