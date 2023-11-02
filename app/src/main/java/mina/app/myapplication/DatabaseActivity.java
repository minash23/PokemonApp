package mina.app.myapplication;

import static mina.app.myapplication.pokemonDBProvider.TABLE_NAME;
import static mina.app.myapplication.pokemonDBProvider._ID;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.GridLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {
    TextView title;
    ListView pokemonListview;
    ArrayList<String> currentPokes;
    pokemonDBProvider.MainDatabaseHelper db;
    pokemonDBProvider DB;

    Button back;
    ArrayList<String> pokemons;

    Cursor mCursor;

    ArrayAdapter<String> adapter;



    public void deleteSelectedItem(String number){
        mCursor = getContentResolver().query(pokemonDBProvider.CONTENT_URI, null, null, null, null);

        mCursor.moveToPosition(Integer.valueOf(number));
        int ID_NUM = mCursor.getInt(0);
        String NationalNumber = mCursor.getString(1);
        String Name = mCursor.getString(2);
        String Species = mCursor.getString(3);
        String Gender = mCursor.getString(4);
        String Height = mCursor.getString(5);
        String Weight = mCursor.getString(6);
        String Level = mCursor.getString(7);
        String HP = mCursor.getString(8);
        String Attack = mCursor.getString(9);
        String Defense = mCursor.getString(10);

        Log.d("SUM", pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString());

        if(pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(NationalNumber) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Name) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Species) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Gender) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Height) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Weight) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Level) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(HP) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Attack) && pokemonListview.getItemAtPosition(Integer.valueOf(number)).toString().contains(Defense)){
            String sel = "_ID = " + ID_NUM;
            Log.d("Selection", sel);
            int rowDelete = getContentResolver().delete(pokemonDBProvider.CONTENT_URI, sel,  null);
        }

        mCursor = getContentResolver().query(pokemonDBProvider.CONTENT_URI, null, null, null, null);

        //DB.updateRemindersIds();
    }

    AdapterView.OnItemLongClickListener longListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            deleteSelectedItem(Integer.toString(i));

            query1();
            return false;
        }
    };

        public void onClick(View view) {
            Intent i = new Intent(this, PokemonActivity.class);
            startActivity(i);
        }



    public void query1(){
            mCursor = getContentResolver().query(pokemonDBProvider.CONTENT_URI, null, null, null, null);
       pokemonListview = findViewById(R.id.pokemon_listview);

        String attributes = "";
        pokemons = new ArrayList<>();
       if (!mCursor.moveToNext()) {
           mCursor.moveToFirst();
       }

        while (!mCursor.isLast()) {
            String NationalNumber = mCursor.getString(1);
            String Name = mCursor.getString(2);
            String Species = mCursor.getString(3);
            String Gender = mCursor.getString(4);
            String Height = mCursor.getString(5);
            String Weight = mCursor.getString(6);
            String Level = mCursor.getString(7);
            String HP = mCursor.getString(8);
            String Attack = mCursor.getString(9);
            String Defense = mCursor.getString(10);

            attributes = "Pokemon Name: " + Name + "\n" + "\n";
            attributes += "Attributes(Separated By Commas in Order of Input): " + "\n" + NationalNumber +", " + Species +", " + Gender  +", " +  Height +", " + Weight +", " + Level +", " + HP +", " + Attack +", " + Defense +", ";
            pokemons.add(attributes);

            mCursor.moveToNext();
            attributes = "";
        }
        String NationalNumber = mCursor.getString(1);
        String Name = mCursor.getString(2);
        String Species = mCursor.getString(3);
        String Gender = mCursor.getString(4);
        String Height = mCursor.getString(5);
        String Weight = mCursor.getString(6);
        String Level = mCursor.getString(7);
        String HP = mCursor.getString(8);
        String Attack = mCursor.getString(9);
        String Defense = mCursor.getString(10);

        attributes = "Pokemon Name: " + Name + "\n" + "\n";
        attributes += "Attributes(Separated By Commas in Order of Input): " + "\n" + NationalNumber +", " + Species +", " + Gender  +", " +  Height +", " + Weight +", " + Level +", " + HP +", " + Attack +", " + Defense +", ";
        pokemons.add(attributes);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokemons);
        pokemonListview.setAdapter(adapter);


        //mCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_database, mCursor, mListColumns, mListItems, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //pokemonListview.setAdapter(mCursorAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        title = findViewById(R.id.database_title);
        pokemonListview = findViewById(R.id.pokemon_listview);
        back = findViewById(R.id.back_button);

        pokemonListview.setOnItemLongClickListener(longListener);
        query1();

    }

}