package com.qw.kronerapp.classes;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.qw.kronerapp.R;

import java.io.IOException;
import java.util.Random;

public class MusicMethod extends Activity {
    private static boolean flag = true;
    public static MediaPlayer player;
    private static int[] songs = {R.raw.lone_djurdjevdan,
            R.raw.lone_od_kad_sam_se_rodio,
            R.raw.lone_samo_pijan_mogu,
            R.raw.lone_bizuterija,
            R.raw.lone_vasko
    };
    private static Random random = new Random();
    private static int randomSong = random.nextInt(songs.length);
    private static int currentSong = 0;
    public static void SoundPlayer(Context ctx){
        currentSong=randomSong;
        if(currentSong>songs.length){
            currentSong=0;
        }
        player = MediaPlayer.create(ctx,songs[randomSong]);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                currentSong++;
                if (currentSong == songs.length) {
                    currentSong = 0;
                }
                player.reset();
                try {
                    player.setDataSource(ctx, Uri.parse("android.resource://" + ctx.getPackageName() + "/" + songs[currentSong]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.start();
            }
        });
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
