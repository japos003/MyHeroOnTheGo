package com.myhero.myheroonthego;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import static android.view.View.VISIBLE;

/**
 * Created by Ryan on 2/25/15.
 */
public class VideoActivity extends ActionBarActivity {

    ProgressDialog pd;
    VideoView vv;

    String VideoURL;
    // placeholder for fetching video title
    //String VideoTitle = "The Bird Maker";
    String VideoTitle;

    String VideoDescription;

    TextView titleView;
    TextView descView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent i = getIntent();

        VideoURL = i.getStringExtra("video_url");
        VideoDescription = i.getStringExtra("desc");
        VideoTitle = i.getStringExtra("title");

        titleView = (TextView) findViewById(R.id.title);
        titleView.setText(VideoTitle);

        descView = (TextView) findViewById(R.id.description);
        descView.setText(VideoDescription);
        descView.setMovementMethod(new ScrollingMovementMethod());

        vv = (VideoView) findViewById(R.id.VideoView);

        pd = new ProgressDialog(VideoActivity.this);
        pd.setTitle("MyHero on the Go Videos");
        pd.setMessage("Buffering...");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();

        try {
            MediaController mc = new MediaController(VideoActivity.this);
            mc.setAnchorView(vv);

            Uri video = Uri.parse(VideoURL);
            vv.setMediaController(mc);
            vv.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        vv.requestFocus();
        vv.setOnPreparedListener(new OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                pd.dismiss();
                vv.start();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
       /* if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.videoview_land);
            titleView.setVisibility(View.GONE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.videoview_main);
        }*/
    }


}

