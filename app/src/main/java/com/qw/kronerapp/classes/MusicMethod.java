package com.qw.kronerapp.classes;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class MusicMethod extends Activity {
    private static boolean flag = true;
    public static MediaPlayer player;
    public static void SoundPlayer(Context ctx, int raw_id){
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(true);
        player.start();
    }
    public static void toggleMusic(Context ctx){
        if(flag==true){
            player.setVolume(0,0);
            flag=false;
        } else{
            player.setVolume(1,1);
            flag=true;
        }
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
