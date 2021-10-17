package com.myapp.mygithubapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.myapp.mygithubapp.R;
import com.myapp.mygithubapp.model.commit.CommitInformation;
import java.util.List;

/**
 * This class provides the data to the view that has to be displayed on UI
 */
public class CommitInfoAdapter extends RecyclerView.Adapter<CommitInfoAdapter.ViewHolder> {
    private static final String TAG = "CommitInfoAdapter";
    private List<CommitInformation> commitInformationList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public CommitInfoAdapter(Context context, List<CommitInformation> data) {
        this.mInflater = LayoutInflater.from(context);
        this.commitInformationList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.commit_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommitInformation commitInformation = commitInformationList.get(position);
        holder.authorNameText.setText(commitInformation.getCommit().getAuthor().getName());
        holder.commitHashText.setText(commitInformation.getSha());
        holder.commitMessageText.setText(commitInformation.getCommit().getMessage());
    }

    @Override
    public int getItemCount() {
        return commitInformationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView authorNameText;
        TextView commitHashText;
        TextView commitMessageText;

        ViewHolder(View itemView) {
            super(itemView);
            authorNameText = itemView.findViewById(R.id.author);
            commitHashText = itemView.findViewById(R.id.commit_hash);
            commitMessageText = itemView.findViewById(R.id.commit_message);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
                Log.i(TAG, "OnItem clicked:"+commitInformationList.get(getAdapterPosition()).getCommit().getMessage());
            }
        }
    }

    CommitInformation getItem(int id) {
        return commitInformationList.get(id);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
