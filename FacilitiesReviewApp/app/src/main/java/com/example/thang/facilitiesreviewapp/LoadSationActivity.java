package com.example.thang.facilitiesreviewapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class LoadSationActivity extends Activity {
    Database database = new Database(this);
    CursorAdapter ca;
    int position;
    String sid = "";
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_sation);
        cursor = database.loadAllStation();
        ca = new SimpleCursorAdapter(
                this,
                R.layout.sation_layout,
                cursor,
                new String[]{"_id", "stationName", "stationType", "stationFacilities", "stationLocation"},
                new int[]{R.id.tvwId, R.id.tvwStationName, R.id.tvwStationType, R.id.tvwStationFacilities, R.id.tvwSationLocation},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        //Access to the view
        ListView lvwStation = (ListView) findViewById(R.id.lvwSation);
        lvwStation.setAdapter(ca);
        registerForContextMenu(lvwStation);
        lvwStation.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                position = arg2;
                cursor.moveToPosition(arg2);
                sid = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
//                Toast.makeText(getApplicationContext(),"UID: "+uid, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//        MenuItem newItem = (MenuItem)findViewById(R.id.Menu);
//        newItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//
//			@Override
//			public boolean onMenuItemClick(MenuItem arg0) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		});

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

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Menu:
                break;
            case R.id.deleteObj:
                break;
        }

        if (item.getTitle().equals("Add Station")) {
            Intent addStation = new Intent(this, AddStationActivity.class);
            ca.getItem(position);
            startActivity(addStation);


        } else if (item.getTitle().equals("Delete")) {
            //call delete function in Database
            database.deleteStation(sid);
            Cursor a = database.loadAllStation();
            ca.swapCursor(a);
            //

//            adapter.remove(listView.getItemAtPosition(position).toString());
        } else if(item.getTitle().equals("Add Review")){
            Intent addReview = new Intent(this, AddReviewActivity.class);
            addReview.putExtra("sid", sid);
            startActivity(addReview);
        }
        return
                false;

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Add Station");
        menu.add("Add Review");
        menu.add("Delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
