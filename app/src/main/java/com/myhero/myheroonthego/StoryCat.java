package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul Madrigal Jr on 3/5/2015.
 */
public class StoryCat implements Serializable {
    //set the different type of tags in the array to this object
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
        return "storylink:" + storyLink  + "~name:" + name + "~imglink:" + imglink + "~Description:" + description;
    }

}
