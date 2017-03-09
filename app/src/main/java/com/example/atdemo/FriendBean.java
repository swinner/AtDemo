package com.example.atdemo;

/**
 * @anthor: gaotengfei
 * @time: 2017/3/9
 * @tel: 18511913443
 * @desc:
 */

public class FriendBean {
    private String id;
    private String name;

    public FriendBean(String name,String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
