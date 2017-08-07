package com.minipg.fanster.armoury.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.minipg.fanster.armoury.view.XpListItem;

/**
 * Created by Knot on 8/7/2017.
 */



public class XpListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        XpListItem item;if (view != null)
//            item = (XpListItem ) view;
//        else
//            item = new XpListItem (viewGroup.getContext());
//        item.setTopicXp("IOS",30,100);
//        return item;
        return new XpListItem(viewGroup.getContext());
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
