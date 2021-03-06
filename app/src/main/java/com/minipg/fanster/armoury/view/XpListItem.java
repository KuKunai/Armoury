package com.minipg.fanster.armoury.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.view.state.BundleSavedState;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class XpListItem extends BaseCustomViewGroup {

    private TextView tvTopic;
    private ProgressBar pbXp;
    private TextView tvXp;

    public XpListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public XpListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public XpListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public XpListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_profile_xp, this);
    }

    private void initInstances() {
        // findViewById here
        tvTopic = (TextView) findViewById(R.id.tvTopic);
        pbXp = (ProgressBar) findViewById(R.id.pbXp);
        tvXp = (TextView) findViewById(R.id.tvXp);
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

    public void setTopicXp(String topic, int value, int max) {
        if (max >= value) {
            tvTopic.setText(topic);
            tvXp.setText(value + " / " + max);
            pbXp.setMax(max);
            pbXp.setProgress(value);
        }
    }

    public void setProgressbar(int value, int max) {
        if (max >= value) {
            pbXp.setMax(max);
            pbXp.setProgress(value);
        }
    }

}
