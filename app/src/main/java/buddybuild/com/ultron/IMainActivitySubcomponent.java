package buddybuild.com.ultron;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface IMainActivitySubcomponent extends AndroidInjector<MainActivity> {


    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}