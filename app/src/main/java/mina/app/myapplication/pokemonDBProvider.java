package mina.app.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class pokemonDBProvider extends ContentProvider {
    public final static String DBName = "PokemonDatabase";
    protected static final class MainDatabaseHelper extends SQLiteOpenHelper{
        MainDatabaseHelper(Context context){
            super(context, DBName, null, 1);
        }
        public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_MAIN);}
        public void onUpgrade (SQLiteDatabase arg0, int arg1, int agr2){

        }
    };

    public final static String TABLE_NAME = "PokemonStats";
    public final static String COLUMN_ONE = "NationalNumber";
    public final static String COLUMN_TWO = "Name";
    public final static String COLUMN_THREE = "Species";
    public final static String COLUMN_FOUR = "Gender";
    public final static String COLUMN_FIVE = "Height";
    public final static String COLUMN_SIX = "Weight";
    public final static String COLUMN_SEVEN = "Level";
    public final static String COLUMN_EIGHT = "HP";
    public final static String COLUMN_NINE = "Attack";
    public final static String COLUMN_TEN = "Defense";
    public final static String _ID = "_id";


    public final static String AUTHORITY = "com.belmont.pokemon";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY +"/" + TABLE_NAME);
    private MainDatabaseHelper mOpenHelper;
    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_NAME +
            "(" +
            " _ID INTEGER PRIMARY KEY, " +
            COLUMN_ONE + " TEXT," +
            COLUMN_TWO + " TEXT," +
            COLUMN_THREE + " TEXT," +
            COLUMN_FOUR + " TEXT," +
            COLUMN_FIVE + " TEXT," +
            COLUMN_SIX + " TEXT," +
            COLUMN_SEVEN + " TEXT," +
            COLUMN_EIGHT + " TEXT," +
            COLUMN_NINE + " TEXT," +
            COLUMN_TEN + " TEXT)";
    public pokemonDBProvider() {

    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String first = values.getAsString(COLUMN_ONE).trim();
        String second = values.getAsString(COLUMN_TWO).trim();
        String third = values.getAsString(COLUMN_THREE).trim();
        String fourth = values.getAsString(COLUMN_FOUR).trim();
        String fifth = values.getAsString(COLUMN_FIVE).trim();
        String sixth = values.getAsString(COLUMN_SIX).trim();
        String seventh = values.getAsString(COLUMN_SEVEN).trim();
        String eighth = values.getAsString(COLUMN_EIGHT).trim();
        String ninth = values.getAsString(COLUMN_NINE).trim();
        String tenth = values.getAsString(COLUMN_TEN).trim();

        if (first.equals("") || second.equals("") || third.equals("") || fourth.equals("") || fifth.equals("") || sixth.equals("") || seventh.equals("") || eighth.equals("") || ninth.equals("") || tenth.equals("")){
            return null;
        }


        long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAME, null, values);

        return Uri.withAppendedPath(CONTENT_URI,""+id);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    };

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder );
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return mOpenHelper.getReadableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
    }


}