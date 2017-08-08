package com.minipg.fanster.armoury.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.view.TopicListItem;


/**
 * Created by Knot on 8/7/2017.
 */



public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.TopicListItemHolder> {

    @Override
    public TopicListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_topic,parent,false);
        return new TopicListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopicListItemHolder holder, int position) {
        holder.tvTitle.setText("Title" + position);
        holder.tvAuthor.setText("By Author");
        holder.tvStory.setText("Story");
        holder.tvDate.setText("Date");
        holder.tvLiked.setText(position + "Liked");
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    static class  TopicListItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvAuthor;
        private final TextView tvStory;
        private final TextView tvDate;
        private final TextView tvLiked;

        public TopicListItemHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvStory = (TextView) itemView.findViewById(R.id.tvStory);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvLiked = (TextView) itemView.findViewById(R.id.tvLiked);
        }
    }
}
