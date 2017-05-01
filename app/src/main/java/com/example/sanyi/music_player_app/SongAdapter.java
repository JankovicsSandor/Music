package com.example.sanyi.music_player_app;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    private boolean inShop;

    public SongAdapter(Activity context, ArrayList<Song> songlist, boolean inShop){
        super(context,0,songlist);
        this.inShop=inShop;
    }

    public SongAdapter(Activity context, ArrayList<Song> songlist, boolean inShop, int newTextColor) {
        super(context, 0, songlist);
        this.inShop = inShop;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        // If the list is empty we infalte a viewgropu for it
        if(listView ==null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.song_item, parent, false);
        }
        Song currentSong = getItem(position);
        // Finding views in the item layout
        ImageView songImage = (ImageView) listView.findViewById(R.id.imageHold);
        TextView nameText = (TextView) listView.findViewById(R.id.songTitle);
        TextView albumText = (TextView) listView.findViewById(R.id.albumTitle);
        ImageView songType = (ImageView) listView.findViewById(R.id.playType);
        TextView priceTag =(TextView) listView.findViewById(R.id.priceTag);
        // Setting the desired values to the views
        songImage.setImageResource(currentSong.getImage());
        nameText.setText(currentSong.getAuthor());
        albumText.setText(currentSong.getAlbum());
        // If we are in the shop we show a cart icon so we can buy that song
        if(inShop){
            songType.setImageResource(R.drawable.ic_add_shopping_cart_black_24dp);
            priceTag.setText(getContext().getString(R.string.dollar)+" "+currentSong.getPrice());
        }
        // If we are somewhere else we just simply hide the price icon as the play buton is the default image
        else {
            priceTag.setVisibility(View.GONE);
        }
        return listView;
    }
}
