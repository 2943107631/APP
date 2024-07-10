package com.user.bean;

public class CommentBean {
    private int exId;
    private String username;
    private String content;

    public CommentBean(int exId, String username, String content) {
        this.exId = exId;
        this.username = username;
        this.content = content;
    }

    public int getExId() {
        return exId;
    }

    public void setExId(int exId) {
        this.exId = exId;
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
}
