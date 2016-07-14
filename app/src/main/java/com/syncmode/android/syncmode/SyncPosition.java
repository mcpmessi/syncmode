package com.syncmode.android.syncmode;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.syncmode.android.persistence.DatabaseMenager;

import java.util.Timer;
import java.util.TimerTask;

public class SyncPosition extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mClienteApi;
    private DatabaseMenager database;
    private static int timeSync = 300000;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");


    }

    @Override
    public void onCreate() {
        database = new DatabaseMenager(getApplicationContext());

        Log.i("SERVIÇO", "Serviço START");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                getLocation();
                verifyTime();
            }
        }, 0, timeSync);

        super.onCreate();
    }

    public void verifyTime(){
        try{
            Log.e("teste", "funciona");
            //int time = Integer.parseInt(database.getTimeSync());
            //SyncPosition.timeSync = time;
        }catch(Exception ex){
            Log.e("ERROR PARSE", ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public synchronized void getLocation() {
        mClienteApi = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mClienteApi.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.e("onConnected", "onConnected:::bundle");


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        Location local = LocationServices.FusedLocationApi.getLastLocation(mClienteApi);

        if(local != null){
            Log.e("LATITUDE:: ", ""+local.getLatitude());
            Log.e("LATITUDE:: ", ""+local.getLongitude());
        }
        mClienteApi.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
