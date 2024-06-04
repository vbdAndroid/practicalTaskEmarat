package com.example.practicaltaskemarat.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters

@Database(entities = [University::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)abstract class AppDatabase : RoomDatabase() {

    abstract fun universityDao(): UniversityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "university_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}