package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [SleepNight::class])
public abstract class SleepDatabase() : RoomDatabase() {

    // You can have multiple DAOs.
    abstract val sleepDatabaseDao: SleepDatabaseDao

    /*
    * The companion object allows clients to access the methods for creating or getting
    * the database without instantiating the class.
    * Since the only purpose of this class is to provide a database,
    * there is no reason to ever instantiate it.*/
    companion object {
        // The value of a volatile variable will never be cached,
        // and all writes and reads will be done to and from the main memory.
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }


}