package com.example.androidrrr.bakingappnd.model;

public class Steps
{
    private String id1;
    private String shortDesc;
    private String desc;
    private String urlLink;
    private String thumLink;

    public Steps(String id, String shortDescription, String description, String videoURL, String thumbnailURL)
    {
        id1=id;
        shortDesc=shortDescription;
        desc=description;
        urlLink=videoURL;
        thumLink=thumbnailURL;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getThumLink() {
        return thumLink;
    }

    public void setThumLink(String thumLink) {
        this.thumLink = thumLink;
    }
}
