package buddybuild.com.ultron;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import buddybuild.com.ultron.model.App;

import java.util.List;

public class AppsRecyclerViewAdapter extends RecyclerView.Adapter<AppsRecyclerViewAdapter.ViewHolder> {

    public interface OnAppClickListener {
        void onAppClick(App app);
    }

    private final List<App> mValues;
    private final OnAppClickListener listener;

    public AppsRecyclerViewAdapter(List<App> items, OnAppClickListener listener) {
        this.mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mValues.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView platformView;
        public final TextView nameView;

        public ViewHolder(View view) {
            super(view);

            platformView = (TextView) view.findViewById(R.id.platform);
            nameView = (TextView) view.findViewById(R.id.name);
        }

        public void bind(final App app, final OnAppClickListener listener) {
            platformView.setText(app.getPlatform());
            nameView.setText(app.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAppClick(app);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameView.getText() + "'";
        }
    }
}
