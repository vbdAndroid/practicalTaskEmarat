package com.example.practicaltaskemarat.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(university: University)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<University>)

    @Query("SELECT * FROM university_table")
    suspend fun getAllUniversities(): List<University>

    @Query("DELETE FROM university_table")
    suspend fun deleteAll()
}