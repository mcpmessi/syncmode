package com.syncmode.android.custom;

/**
 * Created by messi on 15/07/2016.
 */
public class TimeSyncValues {
    private String name;
    private int value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return value;
    }

    public void setId(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return this.name;
    }


}
