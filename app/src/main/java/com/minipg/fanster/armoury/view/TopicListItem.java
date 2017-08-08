package com.minipg.fanster.armoury.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.view.state.BundleSavedState;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class TopicListItem extends BaseCustomViewGroup {

    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvStory;
    private TextView tvDate;
    private TextView tvLiked;

    public TopicListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public TopicListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public TopicListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public TopicListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_topic, this);
    }

    private void initInstances() {
        // findViewById here
        tvTitle = (TextView) findViewById(R.id.tvTopic);
        tvAuthor= (TextView) findViewById(R.id.tvAuthor);
        tvStory = (TextView) findViewById(R.id.tvStory);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvLiked = (TextView) findViewById(R.id.tvLiked);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hight = width * 2 / 3;
        int newHight = MeasureSpec.makeMeasureSpec(hight,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHight);
    }

    public void setTvAuthor(TextView tvAuthor) {
        this.tvAuthor = tvAuthor;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public void setTvStory(TextView tvStory) {
        this.tvStory = tvStory;
    }

    public void setTvLiked(TextView tvLiked) {
        this.tvLiked = tvLiked;
    }
}
