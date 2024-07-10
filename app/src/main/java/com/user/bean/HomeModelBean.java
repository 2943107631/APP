package com.user.bean;

import java.io.Serializable;

public class HomeModelBean implements Serializable {
    private int id;
    private String title;
    private String salary;
    private String content;
    private String username;
    private String address;

    public HomeModelBean(int id, String title, String salary, String content, String username, String address) {
        this.id = id;
        this.title = title;
        this.salary = salary;
        this.content = content;
        this.username = username;
        this.address = address;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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
}
