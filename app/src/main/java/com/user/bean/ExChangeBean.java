package com.user.bean;

import java.io.Serializable;

public class ExChangeBean implements Serializable {
    private int id;
    private String username;
    private String content;
    private String time;
    private int love;

    public ExChangeBean(int id,String username, String content, String time,int love) {
        this.id=id;
        this.username = username;
        this.content = content;
        this.time = time;
        this.love=love;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }
}
