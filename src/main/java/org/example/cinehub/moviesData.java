package org.example.cinehub;
import javafx.scene.chart.PieChart;
import java.util.Date;

public class moviesData {
    private String title;
    private String genre;
    private String duration;
    private String image;
    private Date date;

    public moviesData(String title,String genre,String duration,String image,Date date){
        this.title=title;
        this.genre=genre;
        this.duration=duration;
        this.image=image;
        this.date=date;
    }

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
