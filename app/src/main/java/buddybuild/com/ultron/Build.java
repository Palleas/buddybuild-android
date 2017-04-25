package buddybuild.com.ultron;

import com.squareup.moshi.Json;

class Build {

    @Json(name = "_id")
    private final String id;

    private final String build_status;

    public Build(String id, String build_status) {
        this.id = id;
        this.build_status = build_status;
    }

    public String getId() {
        return id;
    }

    public String getBuildStatus() {
        return build_status;
    }
}
