package buddybuild.com.ultron

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ActivityModule::class, AndroidInjectionModule::class))
interface ApplicationComponent {

    fun inject(application: MyApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(buildsActivity: BuildsActivity)
}