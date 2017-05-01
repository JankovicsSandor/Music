package com.example.sanyi.music_player_app;


public class Song {
    // Song class stores the details about songs
    private String name;
    private String author;
    private String album;
    private int song;
    private int image;
    private double price = 0;

    // Overlaoded constructor because if we are in the shop we need its price but if we are in the storage or somewhere else we dont need the price
    public Song(int newImage, String newName, String newAuthor, String newAlbum, int newSong) {
        image = newImage;
        name = newName;
        author = newAuthor;
        album = newAlbum;
        song = newSong;
    }

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
