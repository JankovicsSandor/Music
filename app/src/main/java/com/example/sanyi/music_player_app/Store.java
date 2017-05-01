package com.example.sanyi.music_player_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Store extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

       Button store = (Button) findViewById(R.id.gotoLibrary);
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Store.this,ListArtist.class);
                startActivity(intent);
            }
        });
        // We are in the store activity
        final ArrayList<Song> shopSongs= new ArrayList<>();
        shopSongs.add(new Song(R.drawable.ic_audiotrack_black_24dp,"Track_1","Author_1","Album_1",R.raw.song_1, 5.25));
        shopSongs.add(new Song(R.drawable.ic_assistant_black_24dp, "Track_2", "Author_2", "Album_2", R.raw.song_2, 2.6));
        shopSongs.add(new Song(R.drawable.ic_child_care_black_24dp,"Track_3","Author_3","Album_3",R.raw.song_3,10.8));
        shopSongs.add(new Song(R.drawable.ic_pie_chart_black_24dp,"Track_4","Author_4","Album_4",R.raw.song_4,1.8));
        shopSongs.add(new Song(R.drawable.ic_insert_emoticon_black_24dp,"Track_5","Author_5","Album_5",R.raw.song_5,0.50));
        // Setting adapter
        SongAdapter songAdapter= new SongAdapter(this,shopSongs,true);
        final ListView listView = (ListView) findViewById(R.id.listViewStore);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(Store.this,getString(R.string.thanks),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
