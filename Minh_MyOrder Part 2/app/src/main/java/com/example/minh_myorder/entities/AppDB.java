package com.example.minh_myorder.entities;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Coffee.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    private static volatile AppDB appDb;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract CoffeeDAO coffeeDAO();

    public static AppDB getInstance(final Context context){
        return appDb == null ? Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "coffee_db")
                .fallbackToDestructiveMigration()
                .build() : appDb;
    }
}
