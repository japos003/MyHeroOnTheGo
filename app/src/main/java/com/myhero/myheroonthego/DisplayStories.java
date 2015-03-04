package com.myhero.myheroonthego;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.*;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

//DISPLAYSTORIES
public class DisplayStories extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_stories);

        ImageView showThis = (ImageView)findViewById(R.id.display_image);

        Bundle dataFromStoriesHeroes = getIntent().getExtras();

        //MUST DISPLAY IMAGE
        try {
            URL imgURL = new URL("http://www.pokezorworld.com/anime/bmp/Pikachupichu.bmp");
            HttpGet httpRequest = null;

            Toast.makeText(getApplicationContext(), "Problem?", Toast.LENGTH_LONG).show();

            httpRequest = new HttpGet(imgURL.toURI());

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = (HttpResponse) httpclient
                    .execute(httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            InputStream input = b_entity.getContent();

            Bitmap bitmap = BitmapFactory.decodeStream(input);

            showThis.setImageBitmap(bitmap);
        }
        catch(Exception e){
            String choiceFromString = dataFromStoriesHeroes.getString("name");
            //Toast.makeText(getApplicationContext(), "Does not Work", Toast.LENGTH_LONG).show();

            TextView displayThis = (TextView) findViewById(R.id.displayThis);
            displayThis.setText(choiceFromString);
        }
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
