package buddybuild.com.ultron.controller;

import java.util.List;

import javax.inject.Inject;

import buddybuild.com.ultron.model.App;
import buddybuild.com.ultron.model.Build;
import buddybuild.com.ultron.model.Buddybuild;
import io.reactivex.Observable;

public class AppsController {

    private final Buddybuild buddybuild;

    @Inject
    public AppsController(Buddybuild buddybuild) {
        this.buddybuild = buddybuild;
    }

    public Observable<List<App>> list() {
        return buddybuild.apps();
    }

    public Observable<List<Build>> builds(String appId) {
        return buddybuild.builds(appId, 100);
    }

    public Observable<Build> trigger(String appId) {
        return buddybuild.trigger(appId, null);
    }
}
