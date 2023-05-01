package com.example.findadjacentwifi;

public class History {
    private String id;
    private String lat;
    private String lnt;
    private String lookupDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLnt() {
        return lnt;
    }

    public void setLnt(String lnt) {
        this.lnt = lnt;
    }

    public String getLookupDate() {
        return lookupDate;
    }

    public void setLookupDate(String lookupDate) {
        this.lookupDate = lookupDate;
    }
}
