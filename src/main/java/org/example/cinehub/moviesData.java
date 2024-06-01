package org.example.cinehub;

import java.sql.Date;

public class moviesData {
    private int id;
    private String title;
    private String genre;
    private String duration;
    private String image;
    private Date date;

    public moviesData(int id,String title,String genre,String duration,String image,Date date2){
        this.id=id;
        this.title=title;
        this.genre=genre;
        this.duration=duration;
        this.image=image;
        this.date=date2;
    }

    public int getId(){return id;}
    public String getTitle(){
        return title;
    }
    public String getGenre(){
        return genre;
    }
    public String getDuration(){
        return duration;
    }
    public String getImage(){
        return image;
    }
    public Date getDate(){
        return date;
    }
}
