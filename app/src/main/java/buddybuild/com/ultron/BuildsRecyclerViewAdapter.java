package buddybuild.com.ultron;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import buddybuild.com.ultron.model.Build;
import butterknife.BindView;

public class BuildsRecyclerViewAdapter extends RecyclerView.Adapter<BuildsRecyclerViewAdapter.BuildHolder> {

    private final List<Build> builds;

    public BuildsRecyclerViewAdapter(List<Build> builds) {
        this.builds = builds;
    }

    @Override
    public BuildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.build_item, parent, false);
        return new BuildsRecyclerViewAdapter.BuildHolder(view);
    }

    @Override
    public void onBindViewHolder(BuildHolder holder, int position) {
        holder.bind(builds.get(position));
    }

    @Override
    public int getItemCount() {
        return builds.size();
    }

    public class BuildHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commit_message) TextView commitMessageView;
        @BindView(R.id.author) TextView authorView;

        public BuildHolder(View itemView) {
            super(itemView);
        }

        public void bind(Build build) {
            commitMessageView.setText(build.getCommit().getMessage());
            authorView.setText(build.getCommit().getAuthor());

            if (build.getBuildStatus().equalsIgnoreCase("cancelled")) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.cancelled));
            } else if (build.getBuildStatus().equalsIgnoreCase("failed")) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.failed));
            } else if (build.getBuildStatus().equalsIgnoreCase("queued")) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.queued));
            } else if (build.getBuildStatus().equalsIgnoreCase("running")) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.running));
            } else if (build.getBuildStatus().equalsIgnoreCase("success")) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.success));
            }
        }
    }
}
