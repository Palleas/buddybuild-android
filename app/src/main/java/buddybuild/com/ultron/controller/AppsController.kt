package buddybuild.com.ultron.controller

import javax.inject.Inject

import buddybuild.com.ultron.model.App
import buddybuild.com.ultron.model.Build
import buddybuild.com.ultron.model.Buddybuild
import buddybuild.com.ultron.viewdata.AppViewData
import io.reactivex.Observable

class AppsController @Inject
constructor(private val buddybuild: Buddybuild) {

    fun list(): Observable<List<App>> {
        return buddybuild.apps()
    }

    fun builds(appId: String): Observable<List<Build>> {
        return buddybuild.builds(appId, 100)
    }

    fun trigger(appId: String): Observable<Build> {
        return buddybuild.trigger(appId, null!!)
    }
}
