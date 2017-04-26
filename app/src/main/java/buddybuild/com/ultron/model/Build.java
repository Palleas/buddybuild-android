package buddybuild.com.ultron.model;

import com.squareup.moshi.Json;

public class Build {

    enum Status {

    }

    @Json(name = "_id")
    private String id;

    private String build_status;

    @Json(name = "commit_info")
    private Commit commit;

    public String getId() {
        return id;
    }

    public String getBuildStatus() {
        return build_status;
    }

    public Commit getCommit() {
        return commit;
    }
}
