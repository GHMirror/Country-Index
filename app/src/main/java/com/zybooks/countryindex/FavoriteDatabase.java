package com.zybooks.countryindex;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

public class FavoriteDatabase extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "favorites.db";
    private static final String TABLE_NAME = "favorites";
    static int VERSION = 1;
    SQLiteDatabase db;

    public FavoriteDatabase(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, NAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(VERSION == oldVersion){
            db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
            VERSION = newVersion;
        }
    }

    void insert(String countryName){
        ContentValues cv = new ContentValues();
        cv.put("NAME", countryName);
        db = getWritableDatabase();
        db.insert(TABLE_NAME, null, cv);
        db.execSQL("INSERT INTO " + TABLE_NAME + "(NAME) VALUES " + "(\"" + countryName + "\");");
    }

    void delete(String countryName){
        db = getWritableDatabase();
        db.delete(TABLE_NAME,"NAME = ?", new String [] {countryName});
    }

    boolean isFavorite(String countryName){
        boolean exists = false;
        db = getReadableDatabase();
        String [] columns = {"NAME"};
        String selection = "name = ?";
        String [] selectionArgs = new String[] {countryName};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            exists = true;
        }
        cursor.close();
        return exists;
    }
}

