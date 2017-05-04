package buddybuild.com.ultron.model

import com.squareup.moshi.Json

class App {

    @Json(name = "_id")
    val id: String? = null

    @Json(name = "app_name")
    val name: String? = null

    val platform: Platform? = null
}
