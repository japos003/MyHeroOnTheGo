package com.myhero.myheroonthego;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

import java.net.URL;
import java.io.InputStream;

//DISPLAYSTORIES
public class DisplayStories extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_stories);

        ImageView showThis = (ImageView)findViewById(R.id.display_image);

        //URL url = new URL("http://i.imgur.com/J2nP95l.jpg");
        //InputStream content = (InputStream)url.getContent();
        //Drawable d = Drawable.createFromStream(content, "src");
        //showThis.setImageDrawable(d);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_stories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //VIDEOS
    public void gotoFilms(View view){
        //startActivity(new Intent(DisplayStories.this, Films.class));
    }

    //ART
    public void gotoArt(View view){
        startActivity(new Intent(DisplayStories.this, Art.class));
    }
}
