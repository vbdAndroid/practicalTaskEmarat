package com.example.practicaltaskemarat.di

import android.content.Context
import androidx.room.Room
import com.example.practicaltaskemarat.dao.UniversityDao
import com.example.practicaltaskemarat.MyApplication
import com.example.practicaltaskemarat.dao.AppDatabase
import com.example.practicaltaskemarat.dao.repository.UniversityRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }



    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "university_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: AppDatabase): UniversityDao {
        return database.universityDao()
    }


    @Provides
    @Singleton
    fun provideUniversityRepository(universityDao: UniversityDao): UniversityRepository {
        return UniversityRepository(universityDao)
    }

}