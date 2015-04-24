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
                "sid text,"
                +"stationName text,"+
                "stationType text,"+
                "stationFacilities text," +
                "stationLocation text )";
        String addReviewTable = "CREATE TABLE ReviewTable("+
                "sid text"+
                "dateReivew, "+
                "typeFacility  text,"+
                "overallRating  text,"+
                "publishReview  text)";
        String insertStation  = "INSERT INTO station(sid,stationName,stationType,stationFacilities,stationLocation) VALUES ('id1','London Bridge', 'Underground','wifi','fewfwefw')";
        db.execSQL(addStation);
        db.execSQL(insertStation);
        db.execSQL(addReviewTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists product ";
        db.execSQL(sql);
        onCreate(db);
    }

    public void addStation(String sid, String stationName,String stationType, String stationFacilities,String stationLocation ){
        //Get access to an object that represent our db
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("sid", sid);
        row.put("stationName", stationName);
        row.put("stationType", stationType);
        row.put("stationFacilities", stationFacilities);
        row.put("stationLocation", stationLocation);
        db.insert("station", null, row);
        db.close();
    }

    public void deleteStation(String sid){
        SQLiteDatabase db = this.getWritableDatabase();
//        String mStatement = "DELETE FROM station WHERE _id = " + sid +"";
//        db.execSQL(mStatement);
        db.delete("station", "sid" + "=\"" + sid +"\"" , null);
        db.close();
    }
    // Add new review by Station id
    public void addReview(String sid,String dateReivew, String typeFacility, String overallRating, String publishReview){
        //Get access to an object that represent our db
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("sid", sid);
        row.put("dateReivew", dateReivew);
        row.put("typeFacility", typeFacility);
        row.put("overallRating", overallRating);
        row.put("publishReview", publishReview);
        db.insert("ReviewTable", null, row);
        db.close();
    }
    SQLiteDatabase db=null;
    public Cursor loadAllStation(){
        //Open connection to the db
        db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "station",
                new String[]{"sid as _id", "stationName", "stationType","stationFacilities","stationLocation"},
                null, null, null, null, null
        );//execute SQL: select * from product
        return cursor;
    }
    public Cursor loadAllReview(){
        //Open connection to the db
        db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "ReviewTable",
                new String[]{"sid as _id", "dateReivew", "typeFacility","overallRating","publishReview"},
                "sid as _id like ?", null, null, null, null
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
