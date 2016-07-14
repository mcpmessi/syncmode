package com.syncmode.android.syncmode;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.syncmode.android.persistence.Database;

public class MainSync extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private Database database;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sync);

        //verificar se o gogle ploay services está instalado
        Integer resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (resultCode == ConnectionResult.SUCCESS) {
            //Do what you want
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0);
            if (dialog != null) {
                //This dialog will help the user update to the latest GooglePlayServices
                dialog.show();
            }
        }

        initComponent();
        createService();
    }

    /**
     * Apenas inicializar os componentes da activity
     * @return void
     **/
    private void initComponent() {
        database = new Database(this);
        db = database.getWritableDatabase();

        final Spinner sp_times = (Spinner) findViewById(R.id.sp_sync_times);
        sp_times.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = sp_times.getSelectedItem().toString();

                ContentValues c = new ContentValues();
                c.put("time", item);

                db.update("time_sync", c, "",null);

                Log.i("OBJECT SELECTED::", item);

                printResult();

                //foi comitado
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void printResult(){
        Cursor c = db.query("time_sync", new String[]{"time"}, null, null, null, null, null);
        while(c.moveToNext()){
            Log.e("Config::: ", c.getString(0));
        }
    }

    synchronized void createService() {
        startService(new Intent(this, SyncPosition.class));
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
