package buddybuild.com.ultron

import android.app.Activity
import android.app.Application

import javax.inject.Inject

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector

class MyApplication : Application(), HasDispatchingActivityInjector {
    @Inject
    internal var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerIApplicationComponent.builder().build()
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    companion object {

        var applicationComponent: IApplicationComponent? = null
            private set
    }
}
