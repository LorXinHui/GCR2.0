package com.example.myapplication.items;

public class NewsItem {
    private String comm_title;
    private String comm_type;
    private String comm_content;
    private int post_id;
    private int user_id;
    private String comm_date;

    public NewsItem(String comm_title, String comm_type, String comm_content) {
        this.comm_title = comm_title;
        this.comm_type = comm_type;
        this.comm_content = comm_content;
    }

    public String getComm_title() {
        return comm_title;
    }

    public void setComm_title(String comm_title) {
        this.comm_title = comm_title;
    }

    public String getComm_type() {
        return comm_type;
    }

    public void setComm_type(String comm_type) {
        this.comm_type = comm_type;
    }

    public String getComm_content() {
        return comm_content;
    }

    public void setComm_content(String comm_content) {
        this.comm_content = comm_content;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComm_date() {
        return comm_date;
    }

    public void setComm_date(String comm_date) {
        this.comm_date = comm_date;
    }
}

