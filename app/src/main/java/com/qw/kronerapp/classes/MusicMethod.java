package com.qw.kronerapp.classes;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class MusicMethod extends Activity {
    public static MediaPlayer player;
    public static void SoundPlayer(Context ctx, int raw_id){
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(true);
        player.start();
    }
//Ne radi
   /* @Override
    protected void onPause() {
        super.onPause();
        if (MusicMethod.player != null) {
            MusicMethod.player.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MusicMethod.player != null) {
            MusicMethod.player.start();
        }
    }*/
}
