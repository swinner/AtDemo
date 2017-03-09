package com.example.atdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @anthor: gaotengfei
 * @time: 2017/3/9
 * @tel: 18511913443
 * @desc:
 */

public class ContactAdapter extends BaseAdapter {
    private  Context ctx;
    private  List<FriendBean> mList;

    public ContactAdapter(Context ctx, List<FriendBean> mList) {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(ctx).inflate(R.layout.item_contact,null);
        TextView tvItem = (TextView)view.findViewById(R.id.tv_item);
        FriendBean friendBean = mList.get(i);
        tvItem.setText(friendBean.getName());
        return view;
    }
}
