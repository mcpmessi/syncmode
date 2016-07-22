package com.syncmode.android.syncmode;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.syncmode.android.serverhost.MyServer;

/**
 * Created by MESSIAS on 22/07/2016.
 */
public class SyncServer extends Service {
    private MyServer myserver = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }

    @Override
    public void onCreate(){

    }

    public SyncServer(){
        try{
            myserver = new MyServer();
            Log.w("SERVER::", "INICIADO");
        }catch(Exception ex){
            Log.e("ERRO SERVER::", ex.getMessage());
        }
    }


}
