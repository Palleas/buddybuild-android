package buddybuild.com.ultron;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface IBuildsActivitySubcomponent extends AndroidInjector<BuildsActivity> {


    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BuildsActivity> {

    }

}