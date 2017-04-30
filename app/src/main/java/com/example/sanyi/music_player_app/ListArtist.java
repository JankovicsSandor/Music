package com.example.sanyi.music_player_app;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListArtist extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mediaPlayer.pause();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMedia();
            }
        }
    };
    private MediaPlayer.OnCompletionListener completeListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMedia();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMedia();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        Button store = (Button) findViewById(R.id.goToShopButt);
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListArtist.this,Store.class);
                startActivity(intent);
            }
        });
        setContentView(R.layout.activity_artists);
        final ArrayList<Song> shopSongs = new ArrayList<>();
        shopSongs.add(new Song(R.drawable.ic_audiotrack_black_24dp, "Track_1", "Author_1", "Album_1", R.raw.song_1, 5.25));
        shopSongs.add(new Song(R.drawable.ic_assistant_photo_black_24dp, "Track_2", "Author_2", "Album_2", R.raw.song_2, 2.6));
        shopSongs.add(new Song(R.drawable.ic_child_care_black_24dp, "Track_3", "Author_3", "Album_3", R.raw.song_3, 10.8));
        shopSongs.add(new Song(R.drawable.ic_pie_chart_black_24dp, "Track_4", "Author_4", "Album_4", R.raw.song_4, 1.8));
        shopSongs.add(new Song(R.drawable.ic_insert_emoticon_black_24dp, "Track_5", "Author_5", "Album_5", R.raw.song_5, 0.50));

        SongAdapter songAdapter = new SongAdapter(this, shopSongs, false);
        ListView listView = (ListView) findViewById(R.id.listViewArtist);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = shopSongs.get(position);
                releaseMedia();
                int state = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (state == audioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(ListArtist.this, song.getSong());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completeListener);
                }
                Toast.makeText(ListArtist.this, "ONCLICK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void releaseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}

