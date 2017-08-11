package com.minipg.fanster.armoury.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.dao.TopicItemDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MFEC on 8/10/2017.
 */

public class DeviceTypeItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TopicItemDao> data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_topic, parent, false);
        return new DeviceTypeItemViewHodler(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DeviceTypeItemViewHodler){
            DeviceTypeItemViewHodler deviceTypeItemViewHodler = (DeviceTypeItemViewHodler) holder;
            //Log.d("ssss", "onBindViewHolder: "+data.get(position).getDescription());
            deviceTypeItemViewHodler.tvAuthor.setText("by "+data.get(position).getPoster());
            deviceTypeItemViewHodler.tvTitle.setText(data.get(position).getHead());
            deviceTypeItemViewHodler.tvStory.setText(data.get(position).getDescription());
        }

    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }

    public void setData(List<TopicItemDao> data) {
        this.data = data;
    }

    public class DeviceTypeItemViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvAuthor)
        TextView tvAuthor;
        @BindView(R.id.tvStory)
        TextView tvStory;
        @BindView(R.id.tvLiked)
        TextView tvLiked;
        @BindView(R.id.cardViewTopic)
        CardView cardViewTopic;
        public DeviceTypeItemViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
