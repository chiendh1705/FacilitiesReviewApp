package com.example.thang.facilitiesreviewapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class LoadSationActivity extends Activity {
    Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_sation);
        Cursor cursor = database.loadAllStation();
        CursorAdapter ca = new SimpleCursorAdapter(
                this,
                R.layout.sation_layout,
                cursor,
                new String[]{"_id", "stationName", "stationType","stationFacilities","stationLocation"},
                new int[]{R.id.tvwId, R.id.tvwStationName, R.id.tvwStationType,R.id.tvwStationFacilities,R.id.tvwSationLocation},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        //Access to the view
        ListView lvwStation = (ListView)findViewById(R.id.lvwSation);
        lvwStation.setAdapter(ca);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_sation, menu);
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
}
