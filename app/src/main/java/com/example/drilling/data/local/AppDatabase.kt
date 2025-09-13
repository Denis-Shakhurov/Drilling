package com.example.drilling.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.drilling.data.local.dao.BoreholeDao
import com.example.drilling.data.local.dao.GeologicalIntervalDao
import com.example.drilling.data.local.dao.TripDao
import com.example.drilling.data.local.entity.BoreholeEntity
import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import com.example.drilling.data.local.entity.TripEntity

@Database(
    entities = [
        BoreholeEntity::class,
        TripEntity::class,
        GeologicalIntervalEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun boreholeDao(): BoreholeDao
    abstract fun tripDao(): TripDao
    abstract fun geologicalIntervalDao(): GeologicalIntervalDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "geology_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}