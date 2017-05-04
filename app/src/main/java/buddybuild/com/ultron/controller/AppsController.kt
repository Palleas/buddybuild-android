package buddybuild.com.ultron.controller

import buddybuild.com.ultron.model.App
import buddybuild.com.ultron.model.Buddybuild
import buddybuild.com.ultron.model.Build
import io.reactivex.Observable
import javax.inject.Inject

class AppsController
@Inject constructor(private val buddybuild: Buddybuild) {

    fun list(): Observable<List<App>> {
        return buddybuild.apps()
    }

    fun builds(appId: String): Observable<List<Build>> {
        return buddybuild.builds(appId, 100)
    }

    fun trigger(appId: String): Observable<Build> {
        return buddybuild.trigger(appId, null)
    }
}
