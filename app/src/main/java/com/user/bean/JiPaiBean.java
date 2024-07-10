package com.user.bean;

import java.io.Serializable;

public class JiPaiBean implements Serializable {
    private int id;
    private String title;
    private String content;
    private String username;
    private String address;
    private String price;

    public JiPaiBean(int id, String title, String content, String username, String address,String price) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.address = address;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
