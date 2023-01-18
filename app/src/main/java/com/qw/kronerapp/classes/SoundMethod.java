package com.qw.kronerapp.classes;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class SoundMethod extends Activity {
    public static MediaPlayer player;
    public static void SoundPlayer(Context ctx, int raw_id){
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(true);
        player.setVolume(100, 100);
        player.start();


        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.reset();
                player.release();
                player=null;
            }
        });
    }
    public static void SoundPlayerNoLoop(Context ctx, int raw_id){
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(false);
        player.setVolume(100, 100);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.reset();
                player.release();
                player=null;
            }
        });
    }

}
