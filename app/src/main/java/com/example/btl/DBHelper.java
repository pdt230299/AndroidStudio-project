package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {

        super(context, "Planet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Planetdetails(name TEXT primary key, url TEXT,des TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Planetdetails");
    }
    public boolean insertplanetdata(String name, String url, String des){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("url", url);
        contentValues.put("des", des);
        long result = DB.insert("Planetdetails",null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }
    public boolean updateplanetdata(String name, String url, String des){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", url);
        contentValues.put("des", des);

        Cursor cursor = DB.rawQuery("Select * from Planetdetails where name =?",new String[]{name});
        if(cursor.getCount() > 0){

            long result = DB.update("Planetdetails",contentValues, "name=?",new String[]{name});

            if(result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    public boolean deletedata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Planetdetails where name =?",new String[]{name});
        if(cursor.getCount() > 0){
            long result = DB.delete("Planetdetails", "name=?",new String[]{name});
            if(result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Planetdetails",null);
        return cursor;
    }
}