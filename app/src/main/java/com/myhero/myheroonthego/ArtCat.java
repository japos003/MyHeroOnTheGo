package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul on 3/6/2015.
 */
public class ArtCat implements Serializable {
    public String getCap() {
        return cap;
    }
    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getartLink() {
        return artLink;
    }
    public void setartLink(String artLink) {
        this.artLink = artLink;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
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

    private String cap;
    private String artLink;
    private String name;
    private String imglink;
    private String artist;

    @Override
    public String toString() {
        return "imglink:" + imglink + "~artist:" + artist;
    }
}
