package com.example.sanyi.music_player_app;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sanyi on 30/04/2017.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    private boolean inShop;
    LayoutInflater infalte;

    public SongAdapter(Activity context, ArrayList<Song> songlist, boolean inShop){
        super(context,0,songlist);
        this.inShop=inShop;
        infalte=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView ==null){
            listView=infalte.inflate(R.layout.song_item,parent,false);
        }
        Song currentSong = getItem(position);
        ImageView songImage = (ImageView) listView.findViewById(R.id.imageHold);
        TextView nameText = (TextView) listView.findViewById(R.id.songTitle);
        TextView albumText = (TextView) listView.findViewById(R.id.albumTitle);
        ImageView songType = (ImageView) listView.findViewById(R.id.playType);
        TextView priceTag =(TextView) listView.findViewById(R.id.priceTag);

        songImage.setImageResource(currentSong.getImage());
        nameText.setText(currentSong.getAuthor());
        albumText.setText(currentSong.getAlbum());

        if(inShop){
            songType.setImageResource(R.drawable.ic_add_shopping_cart_black_24dp);
            priceTag.setText(getContext().getString(R.string.dollar)+" "+currentSong.getPrice());
        }
        else {
            priceTag.setVisibility(View.GONE);
        }
        return listView;
    }
}
