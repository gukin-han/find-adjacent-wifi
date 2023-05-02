package com.example.findadjacentwifi.domain;

public class Bookmark {
    private long id; // use long instead of String for id
    private String bookmarkGroupId;
    private String wifiId;
    private String createdDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookmarkGroupId() {
        return bookmarkGroupId;
    }

    public void setBookmarkGroupId(String bookmarkGroupId) {
        this.bookmarkGroupId = bookmarkGroupId;
    }

    public String getWifiId() {
        return wifiId;
    }

    public void setWifiId(String wifiId) {
        this.wifiId = wifiId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
