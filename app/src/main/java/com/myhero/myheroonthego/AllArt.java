package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul on 3/5/2015.
 */
public class AllArt implements Serializable {
    public String getArtLink() {
        return artlink;
    }
    public void setArtLink(String artlink) {
        this.artlink = artlink;
    }

    public String getName2() {
        return name2;
    }
    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getImglink2() {
        return imglink2;
    }
    public void setImglink2(String imglink2) {
        this.imglink2 = imglink2;
    }

    private String artlink;
    private String name2;
    private String imglink2;

    @Override
    public String toString() {
        return "artlink:" + artlink  + "~ name:" + name2 + "~ imglink:" + imglink2;
    }
}
