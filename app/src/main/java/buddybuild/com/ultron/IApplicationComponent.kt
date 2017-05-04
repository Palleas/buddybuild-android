package buddybuild.com.ultron

import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = arrayOf(ActivityModule::class, AndroidInjectionModule::class))
interface IApplicationComponent {

    fun inject(application: MyApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(buildsActivity: BuildsActivity)
}