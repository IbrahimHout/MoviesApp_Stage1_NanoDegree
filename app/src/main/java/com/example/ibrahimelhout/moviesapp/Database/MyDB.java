package com.example.ibrahimelhout.moviesapp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.ibrahimelhout.moviesapp.Models.Movie;


@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MyDB extends RoomDatabase {


        private static final String TAG = MyDB.class.getSimpleName();
        private static final Object LOCK = new Object();
        private static final String DATABASE_NAME = "fav_movies_db";

        private static MyDB sInstance;

        public static MyDB getInstance(Context context) {
            if (sInstance == null) {
                synchronized (LOCK) {
                    Log.d(TAG, "Creating New Database Instance");
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MyDB.class,
                            MyDB.DATABASE_NAME)
                            .build();
                }
            }
            Log.d(TAG, "Getting the database instance");
            return sInstance;
        }


        public abstract MovieDAO movieDAO();




}
