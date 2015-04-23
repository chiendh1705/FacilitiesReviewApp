package com.example.thang.facilitiesreviewapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddStationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);
    }
    public void onBtnAdd(View view){
        Database  database = new Database (this);

        //Access to the input name/quantity
        EditText txtNameStation = (EditText) findViewById(R.id.txtNameSation);
        Spinner tvspinner = (Spinner) findViewById(R.id.spinner);
        EditText txtFaclitiesStation = (EditText) findViewById(R.id.txtFaclitiesStation);
        EditText txtLocationSation = (EditText) findViewById(R.id.txtLocationSation);
        EditText txtID = (EditText) findViewById(R.id.txtID);
        String nameSation = txtNameStation.getText().toString();
        String typeSation = tvspinner.getSelectedItem().toString();
        String faclitiesSation = txtFaclitiesStation.getText().toString();
        String locationSation = txtLocationSation.getText().toString();
        String id = txtID.getText().toString();

        //Insert
        database.addStation(id,nameSation, typeSation,faclitiesSation,locationSation);
        //Toast
        Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show();
        //Reset the form
        txtNameStation.setText("");
        txtFaclitiesStation.setText("");
        txtLocationSation.setText("");
        txtID.setText("");
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_station, menu);
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
