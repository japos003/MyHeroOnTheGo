package com.myhero.myheroonthego;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;
//test

public class StoriesHeroes extends ActionBarActivity {

    TextView displayThis;
    //public Intent dataFromStories = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories_heroes);


        //String choiceFromString = dataFromStories.getExtras().getString("name");


        //displayThis = (TextView) findViewById(R.id.displayThis);
        //displayThis.setText(choiceFromString);

    //    Toast.makeText(getBaseContext(), choiceFromString, Toast.LENGTH_LONG).show();

    //    displayThis.setText("Hello World!");

        //LISTS
        final String[] listOfValues = new String[] { "Bill Gates", "Thomas Edison,", "Clare Barton", "Ken Kutaragi", "George Lucas", "Donald Trump"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfValues);

        //PUTS TO LISTVIEW
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Bundle bundle = new Bundle();

                //GOES TO DIFFERENT PAGE BASED ON ID

                //String nameOfChoice = listOfValues[(int)id];
                //bundle.putString("name", nameOfChoice);
                //Intent sendToSH = new Intent(StoriesHeroes.this, DisplayStories.class);
                //sendToSH.putExtras(bundle);

                startActivity(new Intent(StoriesHeroes.this, DisplayStories.class));
            }
        });

    }


    /*private void showList(){

        //LISTS
        final String[] listOfValues = new String[] { "Aids", "Angels", "Animals",
                "Artists", "Business", "Community", "Earthkeepers", "Explorers", "Faith",
                "Family", "Freedom", "Health", "Hero's Hero", "Heroes in the News"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfValues);

        //PUTS TO LISTVIEW
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //GOES TO DIFFERENT PAGE BASED ON ID
                Toast.makeText(getBaseContext(), listOfValues[(int) id], Toast.LENGTH_LONG).show();
            }
        });

    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
        //startActivity(new Intent(Art.this, Films.class));
    }

    //ART
    public void gotoArt(View view){
        startActivity(new Intent(StoriesHeroes.this, Art.class));
    }
}
