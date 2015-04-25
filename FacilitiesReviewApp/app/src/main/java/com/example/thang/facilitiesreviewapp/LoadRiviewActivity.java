package com.example.thang.facilitiesreviewapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class LoadRiviewActivity extends Activity {
    Database database = new Database(this);
    CursorAdapter ca;
    int position;
    String sid = "";
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_riview);
        Intent intent = getIntent();
        String sid = intent.getStringExtra("sid");
         cursor = database.loadReviewById(sid);

        ca = new SimpleCursorAdapter(
                this,
                R.layout.review_layout,
                cursor,
                new String[]{"_id", "dateReview", "typeFacility", "overallRating", "publishReview"},
                new int[]{R.id.tvwId, R.id.tvwdateReivew, R.id.tvwtypeFacility, R.id.tvwoverallRating, R.id.tvwpublishReview},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        ListView lvwReview = (ListView)findViewById(R.id.lvwReview);
        lvwReview.setAdapter(ca);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_riview, menu);
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
