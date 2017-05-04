package buddybuild.com.ultron.model

import com.squareup.moshi.Json

class Build {

    internal enum class Status

    @Json(name = "_id")
    val id: String? = null

    val buildStatus: String? = null

    @Json(name = "commit_info")
    val commit: Commit? = null
}
