package com.example.thang.facilitiesreviewapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thang on 4/23/2015.
 */
public class Database extends SQLiteOpenHelper{
    private  static final String DB_NAME = "stationdb.db";
    private  static final int DB_VERSION = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String addStation = "create table station("+
                "_id integer primary key,"
                +"stationName text,"+
                "stationType text,"+
                "stationFacilities text," +
                "stationLocation text )";
        String insertStation  = "INSERT INTO station(stationName,stationType,stationFacilities,stationLocation) VALUES ('London Bridge', 'Underground','wifi','fewfwefw')";
        db.execSQL(addStation);
        db.execSQL(insertStation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists product ";
        db.execSQL(sql);
        onCreate(db);
    }
    public void addStation( String stationName,String stationType, String stationFacilities,String stationLocation ){
        //Get access to an object that represent our db
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("stationName", stationName);
        row.put("stationType", stationType);
        row.put("stationFacilities", stationFacilities);
        row.put("stationLocation", stationLocation);
        db.insert("station", null, row);
        db.close();
    }
    SQLiteDatabase db=null;
    public Cursor loadAllStation(){
        //Open connection to the db
        db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "station",
                new String[]{"_id", "stationName", "stationType","stationFacilities","stationLocation"},
                null, null, null, null, null
        );//execute SQL: select * from product
        return cursor;
    }

    @Override
    protected void finalize() throws Throwable {
        if(db!=null){
            try{
                db.close();
            }catch(Exception ex){
                //Do nothing
            }
        }
        super.finalize();
    }
}
