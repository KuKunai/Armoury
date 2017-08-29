package com.minipg.fanster.armoury.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.activity.TopicActivity;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.fragment.TabLikedFragment;
import com.minipg.fanster.armoury.fragment.TabPopularFragment;
import com.minipg.fanster.armoury.fragment.TopicListFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Knot on 8/7/2017.
 */



public class LikedTopicListAdapter extends RecyclerView.Adapter<LikedTopicListAdapter.TopicListItemHolder> {

    TabLikedFragment fragmentTopic;
    List<TopicItemDao> topicList;
    TabLikedFragment fragmentTopic1;
    final String KEY_HEAD = "head";
    final String KEY_DESC = "desc";
    final String KEY_LINK = "link";
    final String KEY_LIKE = "like";
    final String KEY_POSTER = "author";
    final String KEY_DATE = "date";
    final String KEY_ID = "topicId";

    public LikedTopicListAdapter(TabLikedFragment fragmentTopic,
                                 List<TopicItemDao> topicList, TabLikedFragment fragmentTopic1) {
        this.fragmentTopic = fragmentTopic;
        this.topicList = topicList;
        this.fragmentTopic1 = fragmentTopic1;
    }

    public LikedTopicListAdapter(){
    }

    @Override
    public TopicListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_topic,parent,false);
        return new TopicListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopicListItemHolder holder, final int position) {
        if (topicList != null) {
            holder.tvTitle.setText(topicList.get(position).getTitle());
            holder.tvAuthor.setText("by " + topicList.get(position).getPoster());
            holder.tvStory.setText(topicList.get(position).getDescription());
            holder.tvDate.setText(topicList.get(position).getCategory());
            holder.tvCate.setText(topicList.get(position).getScore() + " liked");
            holder.tvLiked.setText(topicList.get(position).getView() + " views");

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmentTopic.getActivity(), TopicActivity.class);
                if (topicList != null) {
                    Bundle topicBundle = new Bundle();
                    topicBundle.putString(KEY_HEAD, topicList.get(position).getTitle());
                    topicBundle.putString(KEY_DESC, topicList.get(position).getDescription());
                    topicBundle.putLong(KEY_DATE, topicList.get(position).getCreateDate());
                    topicBundle.putString(KEY_ID, topicList.get(position).getId());
                    topicBundle.putString(KEY_LINK, topicList.get(position).getLink());
                    topicBundle.putString(KEY_POSTER, topicList.get(position).getPoster());
                    topicBundle.putInt(KEY_LIKE, topicList.get(position).getScore());
                    intent.putExtra("topicBundle", topicBundle);
                }
                fragmentTopic1.startActivity(intent);
            }
        });
    }

    public String convertUnixToDate(Long date) {
        Date dateT = new Date();
        dateT.setTime(date * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-7"));
        String formattedDate = sdf.format(dateT);
        return formattedDate;
    }

    @Override
    public int getItemCount() {
        if (topicList == null)
            return 5;
        return topicList.size();
    }


    static class  TopicListItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvAuthor;
        private final TextView tvStory;
        private final TextView tvDate;
        private final TextView tvLiked;
        private final CardView cardView;
        private final TextView tvCate;

        public TopicListItemHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewTopic);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvStory = (TextView) itemView.findViewById(R.id.tvStory);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvLiked = (TextView) itemView.findViewById(R.id.tvLiked);
            tvCate = (TextView) itemView.findViewById(R.id.tvCate);
        }
    }

    public void setData(List<TopicItemDao> data) {
        if (data != null) {
            this.topicList = data;
        }
        Log.d("dataaaa", "onResponse: " + data);
    }
}
