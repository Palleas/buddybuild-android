package buddybuild.com.ultron

import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import buddybuild.com.ultron.model.Build
import butterknife.BindView
import butterknife.ButterKnife

class BuildsRecyclerViewAdapter(private val builds: List<Build>) : RecyclerView.Adapter<BuildsRecyclerViewAdapter.BuildHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.build_item, parent, false)
        return BuildHolder(view)
    }

    override fun onBindViewHolder(holder: BuildHolder, position: Int) {
        holder.bind(builds[position])
    }

    override fun getItemCount(): Int {
        return builds.size
    }

    inner class BuildHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.commit_message) lateinit var commitMessageView: TextView
        @BindView(R.id.author) lateinit var authorView: TextView

        init {

            ButterKnife.bind(this, itemView)
        }

        fun bind(build: Build) {
            commitMessageView!!.text = build.commit!!.message
            authorView!!.text = build.commit!!.author

            if (build.buildStatus.equals("cancelled", ignoreCase = true)) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.cancelled))
            } else if (build.buildStatus.equals("failed", ignoreCase = true)) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.failed))
            } else if (build.buildStatus.equals("queued", ignoreCase = true)) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.queued))
            } else if (build.buildStatus.equals("running", ignoreCase = true)) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.running))
            } else if (build.buildStatus.equals("success", ignoreCase = true)) {
                itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.success))
            }
        }
    }
}
