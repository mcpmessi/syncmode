package com.syncmode.android.persistence;


import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.ResultSet;

/**
 * Created by messi on 13/07/2016.
 */
public class Database extends SQLiteOpenHelper{

    private SQLiteDatabase database;
    private final String TIME_SYNC = "time_sync";

    private String CREATE_DATABASE =
            "CREATE TABLE IF not EXISTS time_sync(" +
            "id integer PRIMARY Key AUTOINCREMENT, " +
            "time VARCHAR(128) not null)";

    private static final String DATABASE_NAME = "settings.db";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        db.execSQL(CREATE_DATABASE);
        db.execSQL("insert into time_sync (time) values(\"5\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
