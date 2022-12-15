package com.example.kronerapp.classes;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class AppLifeCycleHelper implements Application.ActivityLifecycleCallbacks {
    // I use two separate variables here. You can, of course, just use one and
// increment/decrement it instead of using two and incrementing both.
    private static int resumed;
    private static int stopped;

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        if (MusicMethod.player != null) {
            MusicMethod.player.start();
        }
    }

    public void onActivityPaused(Activity activity){
            if (MusicMethod.player != null) {
                MusicMethod.player.pause();
            }
        }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }


    /**
     * Checks whether App is visible to the user or not
     * @return true if visible and false otherwise
     */

}

