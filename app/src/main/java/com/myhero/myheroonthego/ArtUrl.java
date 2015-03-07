package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul on 3/7/2015.
 */
public class ArtUrl implements Serializable {
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTitle4() {
        return title;
    }
    public void setTitle4(String title) {
        this.title = title;
    }

    public String getDescription8() {
        return description;
    }
    public void setDescription8(String description) {
        this.description = description;
    }

    public String getImgLink() {
        return imglink;
    }
    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String caption;
    private String title;
    private String imglink;
    private String artist;
    private String description;

    @Override
    public String toString() {
        return "imglink2:" + imglink + "~artist:" + artist;
    }
}
