package com.myhero.myheroonthego;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;


public class Art extends ActionBarActivity {
    private String y = "";
    private String z = " ";
    private String partial_url = " ";
    private String full_url = "http://";
    private ArrayList<AllArt> art;
    private ArrayList<ArtCat> artcat;
    private ArrayList<ArtUrl> arturl;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_art);
        //once the user click on the art button
        //start to find all art tags
        if(art != null) {
            art.clear();
        }
        if (artcat != null) {
            artcat.clear();
        }
        if (arturl != null) {
            arturl.clear();
        }
        SearchAllArt saa = new SearchAllArt();
        saa.execute();
    }

    class SearchAllArt extends AsyncTask<String, Integer, ArrayList<AllArt2>> {
        //call to get all a list of all art tags
        @Override
        protected ArrayList<AllArt2> doInBackground(String... params) {
            executeGetAllArt getallart = new executeGetAllArt();
            art = getallart.GetAllArt();
            executeGetAllArt2 getallart2 = new executeGetAllArt2();
            ArrayList<AllArt2> art2 = getallart2.GetAllArt2();
            return art2;
        }

        @Override
        protected void onPostExecute(ArrayList<AllArt2> allart2) {
            //once we get all art tags, call to display as a list
            ArrayAdapter<AllArt2> artAdapter2 = new ArrayAdapter<AllArt2>(Art.this, android.R.layout.simple_list_item_1, allart2);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_art);
            registerClickCallback();
            populateListView(artAdapter2);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    class SearchArtCat extends AsyncTask<String, Integer, ArrayList<ArtCat2>> {
        //call to get all a list of all art tags
        @Override
        protected ArrayList<ArtCat2> doInBackground(String... params) {
            executeGetArtCat getartcat = new executeGetArtCat();
            artcat = getartcat.GetArtCategory(y);
            executeGetArtCat2 getartcat2 = new executeGetArtCat2();
            ArrayList<ArtCat2> art2 = getartcat2.GetArtCategory2(y);
            return art2;
        }

        @Override
        protected void onPostExecute(ArrayList<ArtCat2> artcat2) {
            //once we get all art tags, call to display as a list
            ArrayAdapter<ArtCat2> artAdapter2 = new ArrayAdapter<ArtCat2>(Art.this, android.R.layout.simple_list_item_1, artcat2);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_art);
            registerClickCallback();
            populateListView2(artAdapter2);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    class SearchArt extends AsyncTask<String, Integer, ArrayList<ArtUrl>> {
        //call to get all a list of all art tags
        @Override
        protected ArrayList<ArtUrl> doInBackground(String... params) {
            //executeGetArt getart = new executeGetArt();
            //art = getart.GetArtCategory(y);
            executeGetArt getart = new executeGetArt();
            ArrayList<ArtUrl> arturl = getart.GetArt(z);
            return arturl;
        }

        @Override
        protected void onPostExecute(ArrayList<ArtUrl> artURL) {
            //once we get all art tags, call to display as a list
            ArrayAdapter<ArtUrl> artAdapter2 = new ArrayAdapter<ArtUrl>(Art.this, android.R.layout.simple_list_item_1, artURL);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_art);
            partial_url = artURL.get(0).toString();
            String[] parts = partial_url.split("~");
            String tag = parts[0];
            if (tag.contains(":")) {
                String[] xparts = tag.split(":");
                String tag2 = xparts[1];
                full_url = full_url + tag2;
                //arturl.clear();
                new DownloadImageTask((ImageView) findViewById(R.id.img)).execute(full_url);
            }
            //registerClickCallback();
            //populateListView3(artAdapter2);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    //display single image
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private void populateListView(ArrayAdapter<AllArt2> c) {
        //set the view to display all art tags
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(c);
    }

    private void populateListView2(ArrayAdapter<ArtCat2> d) {
        //set the view to display all art tags
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(d);
    }

    //private void populateListView3(ArrayAdapter<ArtUrl> e) {
        //set the view to display all art tags
      //  ListView list = (ListView) findViewById(R.id.listView);
       // list.setAdapter(e);
    //}

    private void registerClickCallback() {
        //this function determines if the user clicks
        //a part of the list
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String tosplit = " ";
                if(art != null && !art.isEmpty()) {
                    tosplit = art.get(position).toString();
                }
                else if (artcat != null && !artcat.isEmpty()) {
                    tosplit = artcat.get(position).toString();
                }
                else if (arturl != null && !arturl.isEmpty()) {
                    tosplit = arturl.get(position).toString();
                }
                if (tosplit.contains("~")) {
                    String[] parts = tosplit.split("~");
                    String tag = parts[0];
                    if (tag.contains(":")) {
                        String[] xparts = tag.split(":");
                        String tag2 = xparts[1];
                        if(tosplit.contains("artlink:")) {
                            y = tag2;
                            art.clear();
                            SearchArtCat sac = new SearchArtCat();
                            sac.execute();
                        }
                        else if (tosplit.contains("artLink2")) {
                            z = tag2;
                            artcat.clear();
                            SearchArt sa = new SearchArt();
                            sa.execute();
                        }
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    //ART
    public void gotoFilms(View view){
        startActivity(new Intent(Art.this, Films.class));
    }

    //STORIES
    public void gotoStories(View view){
        startActivity(new Intent(Art.this, Stories.class));
    }
}
