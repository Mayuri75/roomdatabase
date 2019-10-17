package com.example.dbr;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {University.class}, version = 1)
public  abstract  class SampleDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
