package com.example.sanyi.music_player_app;

/**
 * Created by Sanyi on 30/04/2017.
 */

public class Song {
    private String name;
    private String author;
    private String album;
    private int song;
    private int image;
    private double price;


    public Song(int newImage, String  newName, String newAuthor, String newAlbum, int newSong,double newPrice){
        image= newImage;
        name=newName;
        author=newAuthor;
        album=newAlbum;
        song= newSong;
        price= newPrice;
    }
    public  String getName(){return  name;}
    public String getAuthor(){return author;}
    public String getAlbum(){return  album;}
    public int getSong(){return song;}
    public int getImage(){return image;}
    public double  getPrice(){return price;}

}
