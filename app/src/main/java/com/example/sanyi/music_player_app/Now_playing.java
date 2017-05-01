package com.example.sanyi.music_player_app;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Now_playing extends AppCompatActivity {
    // Inicializing the mediaplayers and audio managers
    private MediaPlayer actualMediaPlayer;
    private AudioManager actualAudiomanger;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                actualMediaPlayer.pause();
            }
            else if(focusChange== AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
            else if(focusChange== AudioManager.AUDIOFOCUS_GAIN){
                actualMediaPlayer.start();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mComplitionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    // adding Onclick listeners to the play/ reset/ etc buttons
   private View.OnClickListener onClickListener= new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           switch (v.getId()){
               case R.id.randomId:{
                   ShowToast(getString(R.string.pressedRandom));
                   break;
               }
               case R.id.prevId:{
                   ShowToast(getString(R.string.pressedPrev));
                   break;
               }
               case R.id.playId:{
                   ShowToast(getString(R.string.playPressed));
                   releaseMediaPlayer();
                   int result = actualAudiomanger.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                   if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                       actualMediaPlayer = MediaPlayer.create(Now_playing.this, R.raw.song_2);
                       actualMediaPlayer.start();
                       actualMediaPlayer.setOnCompletionListener(mComplitionListener);
                       break;
                   }
               }
               case R.id.skipId:{
                   ShowToast(getString(R.string.skipPressed));
                   break;
               }
               case R.id.wholeResetId:{
                   ShowToast(getString(R.string.resetWhole));
                   break;
               }
           }
       }
   };

    // Writing a method to display a toast method so we can avoid code peretions
    private void ShowToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        actualAudiomanger = (AudioManager) getSystemService(AUDIO_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        ImageView random =(ImageView) findViewById(R.id.randomId);
        ImageView prev =(ImageView) findViewById(R.id.prevId);
        ImageView play =(ImageView) findViewById(R.id.playId);
        ImageView next =(ImageView) findViewById(R.id.skipId);
        ImageView whole =(ImageView) findViewById(R.id.wholeResetId);
        // Setting onclick listeners in a global way to avoid code repeating
        random.setOnClickListener(onClickListener);
        prev.setOnClickListener(onClickListener);
        play.setOnClickListener(onClickListener);
        next.setOnClickListener(onClickListener);
        whole.setOnClickListener(onClickListener);
    }
       private void releaseMediaPlayer() {
           // If the media player is not null, then it may be currently playing a sound.
           if (actualMediaPlayer != null) {
               // Regardless of the current state of the media player, release its resources
               // because we no longer need it.
               actualMediaPlayer.release();

               // Set the media player back to null. For our code, we've decided that
               // setting the media player to null is an easy way to tell that the media player
               // is not configured to play an audio file at the moment.
               actualMediaPlayer = null;
               actualAudiomanger.abandonAudioFocus(mOnAudioFocusChangeListener);
           }
       }
}
