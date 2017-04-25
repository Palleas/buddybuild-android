package buddybuild.com.ultron.model;

import com.squareup.moshi.Json;

class Build {

    enum Status {

    }

    @Json(name = "_id")
    private String id;

    private String build_status;

    public String getId() {
        return id;
    }

    public String getBuildStatus() {
        return build_status;
    }
}
