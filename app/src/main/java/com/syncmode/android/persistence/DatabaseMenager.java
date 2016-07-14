package com.syncmode.android.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by MESSIAS on 14/07/2016.
 */
public class DatabaseMenager{

    private SQLiteDatabase db;
    private Database database;

    public DatabaseMenager(Context c){

        this.database = new Database(c);
        this.db = database.getWritableDatabase();

    }

    public String getTimeSync(){
        try{
            Cursor c = db.rawQuery("select time from time_sync", null);

            if(c!=null && c.moveToNext()){
                Log.e("Config::: ", c.getString(c.getColumnIndex("time")));
                return c.getString(c.getColumnIndex("time"));
            }
        }catch(Exception ex){
            Log.e("getConfigSyncTimeId", ex.getMessage());
        }

        return null;
    }

    public int getConfigSyncID(){
        try{
            Cursor c = db.rawQuery("select * from time_sync", null);

            if(c!=null && c.moveToNext()){
                Log.e("Config::: ", c.getString(c.getColumnIndex("time")));
                return c.getInt(c.getColumnIndex("id"));
            }
        }catch(Exception ex){
            Log.e("getConfigSyncTimeId", ex.getMessage());
        }

        return -1;
    }

    /**
     * Alterar o tempo de sincronização na tabela time_sync
     * @param time String
     **/
    public int setConfigTimeSync(String time){
        ContentValues c = new ContentValues();
        c.put("time", time);
        return db.update("time_sync", c, "id="+ getConfigSyncID(), null);
    }
}
