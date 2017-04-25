package buddybuild.com.ultron.model;

import com.squareup.moshi.Json;

public class App {

    @Json(name = "_id")
    private String id;

    @Json(name = "app_name")
    private String name;

    // TODO: 2017-04-25 Add enum
    private String platform;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }
}
