package com.example.thang.facilitiesreviewapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddReviewActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);


    }


    public void onBtnAddReview(View view){
        Database  database = new Database (this);
        //Access to the input name/quantity
        EditText txtDateReview = (EditText) findViewById(R.id.txtDateReview);
        EditText txtTypeFacility = (EditText) findViewById(R.id.txtTypeFacility);
        EditText txtOverallRating = (EditText) findViewById(R.id.txtOverallRating);
        EditText txtPublishReview = (EditText) findViewById(R.id.txtPublishReview);
        String dateReview = txtDateReview.getText().toString();
        String typeFacility = txtTypeFacility.getText().toString();
        String overallRating = txtOverallRating.getText().toString();
        String publishReview = txtPublishReview.getText().toString();
        Intent intent = getIntent();
        String sid = intent.getStringExtra("sid");
        Toast.makeText(this,"sid"+sid , Toast.LENGTH_SHORT).show();


        //Insert
        database.addReview(sid,dateReview, typeFacility, overallRating, publishReview);
        //Toast
        Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show();
        //Reset the form
        txtDateReview.setText("");
        txtTypeFacility.setText("");
        txtOverallRating.setText("");
        txtPublishReview.setText("");
        super.onBackPressed();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_review, menu);
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
