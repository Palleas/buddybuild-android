package buddybuild.com.ultron

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import buddybuild.com.ultron.model.App
import butterknife.BindView
import butterknife.ButterKnife

class AppsRecyclerViewAdapter(private val mValues: List<App>, private val listener: AppsRecyclerViewAdapter.OnAppClickListener) : RecyclerView.Adapter<AppsRecyclerViewAdapter.ViewHolder>() {

    interface OnAppClickListener {
        fun onAppClick(app: App)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position], listener)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.platform) internal var platformView: TextView? = null
        @BindView(R.id.name) internal var nameView: TextView? = null

        init {

            ButterKnife.bind(this, itemView)
        }

        fun bind(app: App, listener: OnAppClickListener) {
            platformView!!.setText(if (app.platform == null) "No platform" else app.platform)
            nameView!!.text = app.name

            itemView.setOnClickListener { listener.onAppClick(app) }
        }

        override fun toString(): String {
            return super.toString() + " '" + nameView!!.text + "'"
        }
    }
}
