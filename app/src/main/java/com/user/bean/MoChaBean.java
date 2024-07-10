package com.user.bean;

public class MoChaBean {
    private int id;
    private String image;
    private String content;
    private String username;
    private int userId;

    public MoChaBean(int id, String image, String content, String username, int userId) {
        this.id = id;
        this.image = image;
        this.content = content;
        this.username = username;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
