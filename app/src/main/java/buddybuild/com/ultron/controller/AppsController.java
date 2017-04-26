package buddybuild.com.ultron.controller;

import java.util.List;

import javax.inject.Inject;

import buddybuild.com.ultron.model.App;
import buddybuild.com.ultron.model.Buddybuild;
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

}
