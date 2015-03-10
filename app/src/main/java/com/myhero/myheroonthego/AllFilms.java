package com.myhero.myheroonthego;

/**
 * Created by Jon Apostol on 3/6/2015.
 */
public class AllFilms {
    public String getFilmLink() {
        return movielink;
    }
    public void setFilmLink(String movielink) {
        this.movielink = movielink;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String movielink;
    private String videolink;
    private String videoDesc;

    public String getVideoLink() {return videolink;}
    public void setVideoLink(String videolink) {this.videolink = videolink;}

    public String getVideoDesc() {return videoDesc;}
    public void setVideoDesc(String videoDesc) {this.videoDesc = videoDesc;}

    @Override
    public String toString() {
        return "Title:" + name;  //+ "~ movielink:" + movielink;
    }

    public String getLink() { return videolink; }
    public String getDesc() { return videoDesc; }
    public String getTitle() { return name; }


}
