package com.syncmode.android.syncmode;


import android.os.AsyncTask;

/**
 * Created by messi on 07/07/2016.
 */
public class HttpSendLog extends AsyncTask<String, Void, String> {

    String [] location;


    public HttpSendLog(String latitude, String longitude){

        //inicializar o objeto para enviar
        this.location = new String[]{
                latitude,
                longitude
        };
    }


    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
