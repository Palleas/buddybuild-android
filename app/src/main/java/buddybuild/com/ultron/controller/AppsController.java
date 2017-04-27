package buddybuild.com.ultron.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import buddybuild.com.ultron.ActivityModule;
import buddybuild.com.ultron.model.App;
import buddybuild.com.ultron.model.Build;
import buddybuild.com.ultron.model.Buddybuild;
import dagger.Component;
import retrofit2.Call;

public class AppsController {

    private final Buddybuild buddybuild;

    @Inject
    public AppsController(Buddybuild buddybuild) {
        this.buddybuild = buddybuild;
    }

    public Call<List<App>> list() {
        return buddybuild.apps();
    }

    public Call<List<Build>> builds(String appId) {
        return buddybuild.builds(appId, 100);
    }

    public Call<Build> trigger(String appId) {
        return buddybuild.trigger(appId, null);
    }
}
