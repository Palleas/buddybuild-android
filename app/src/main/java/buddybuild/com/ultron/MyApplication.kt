package buddybuild.com.ultron

import android.app.Application

class MyApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
                .activityModule(ActivityModule)
                .build()
    }

}
