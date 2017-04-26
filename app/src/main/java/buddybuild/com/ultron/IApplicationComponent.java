package buddybuild.com.ultron;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {ActivityModule.class, AndroidInjectionModule.class})
public interface IApplicationComponent {
    void inject(MyApplication application);
}