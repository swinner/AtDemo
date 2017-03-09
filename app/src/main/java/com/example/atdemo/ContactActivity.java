package com.example.atdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor: gaotengfei
 * @time: 2017/3/9
 * @tel: 18511913443
 * @desc:
 */

public class ContactActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView lvListview;
    private List<FriendBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        lvListview = (ListView)findViewById(R.id.lv_listview);
        list.add(new FriendBean("杨康","杨康"));
        list.add(new FriendBean("郭靖","郭靖"));
        list.add(new FriendBean("小龙女","小龙女"));
        list.add(new FriendBean("杨过","杨过"));
        list.add(new FriendBean("张无忌","张无忌"));
        list.add(new FriendBean("令狐冲","令狐冲"));
        list.add(new FriendBean("任盈盈","任盈盈"));

        ContactAdapter adapter = new ContactAdapter(this,list);
        lvListview.setAdapter(adapter);
        lvListview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FriendBean itemBean = (FriendBean)adapterView.getItemAtPosition(i);
        Intent intent = new Intent();
        intent.putExtra("id",itemBean.getId());
        intent.putExtra("name",itemBean.getName());
        setResult(RESULT_OK,intent);
        finish();
    }
}
