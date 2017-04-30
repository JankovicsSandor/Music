package com.example.sanyi.music_player_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button shop = (Button) findViewById(R.id.shopId);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Store.class);
                startActivity(intent);
            }
        });
        Button artist = (Button) findViewById(R.id.artistId);
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListArtist.class);
                startActivity(intent);
            }
        });
        Button detail = (Button) findViewById(R.id.detailId);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Detail.class);
                startActivity(intent);
            }
        });
        Button nowPlaying = (Button) findViewById(R.id.nowPlayingId);
        nowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Now_playing.class);
                startActivity(intent);
            }
        });
    }
}
