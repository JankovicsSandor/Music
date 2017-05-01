package com.example.sanyi.music_player_app;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {

    // Setting audiofocus listeners (used as a base the miwok app)
    private MediaPlayer SongmediaPlayer;
    private AudioManager SongaudioManager;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                SongmediaPlayer.pause();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                SongmediaPlayer.start();
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
        // Requesting audio focus
        SongaudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView detail = (TextView) findViewById(R.id.detailArtistId);
        detail.setMovementMethod(new ScrollingMovementMethod());
        // Setting onclick listeneres so we can navigate between activities
        Button gotoShop = (Button) findViewById(R.id.goToShopDetail);
        Button gotoItem = (Button) findViewById(R.id.goToLibraryDetail);

        gotoShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detail.this, Store.class);
                startActivity(i);
            }
        });
        gotoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detail.this, ListArtist.class);
                startActivity(i);
            }
        });
        // Defininig which songs we have in our storage from that author into a list and writing out into a list based on song_item.xml and its adapter
        final ArrayList<Song> shopSongs = new ArrayList<>();
        shopSongs.add(new Song(R.drawable.ic_audiotrack_white_24dp, "Track_1", getString(R.string.DetailAuthorName), "Album_1", R.raw.song_1));
        shopSongs.add(new Song(R.drawable.ic_assistant_photo_white_24dp, "Track_2", getString(R.string.DetailAuthorName), "Album_2", R.raw.song_2));
        shopSongs.add(new Song(R.drawable.ic_child_care_white_24dp, "Track_3", getString(R.string.DetailAuthorName), "Album_3", R.raw.song_3));
        shopSongs.add(new Song(R.drawable.ic_pie_chart_white_24dp, "Track_4", getString(R.string.DetailAuthorName), "Album_4", R.raw.song_4));
        shopSongs.add(new Song(R.drawable.ic_insert_emoticon_white_24dp, "Track_5", getString(R.string.DetailAuthorName), "Album_5", R.raw.song_5));

        // Setting adapter
        SongAdapter songAdapter = new SongAdapter(this, shopSongs, false);
        ListView listView = (ListView) findViewById(R.id.DetailList);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = shopSongs.get(position);
                releaseMedia();
                int state = SongaudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (state == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    SongmediaPlayer = MediaPlayer.create(Detail.this, song.getSong());
                    SongmediaPlayer.start();
                    SongmediaPlayer.setOnCompletionListener(completeListener);
                }
                // Feedback so user can see the event hapenning
                Toast.makeText(Detail.this, "ONCLICK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Releasing media player so it doesnt take place
    private void releaseMedia() {
        if (SongmediaPlayer != null) {
            SongmediaPlayer.release();
            SongmediaPlayer = null;
            SongaudioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
