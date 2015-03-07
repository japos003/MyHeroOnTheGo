package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul on 3/6/2015.
 */
public class ArtCat2 implements Serializable {
    public String getstoryLink() {
        return storyLink;
    }
    public void setStoryLink(String storyLink) {
        this.storyLink = storyLink;
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

    public String getdescription() {
        return description;
    }
    public void setdescription(String description) {
        this.description = description;
    }

    private String storyLink;
    private String name;
    private String imglink;
    private String description;

    @Override
    public String toString() {
        return "storylink:" + storyLink  + "~name:" + name + "~Description:" + description;
    }
}
