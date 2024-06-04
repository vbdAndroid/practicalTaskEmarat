package com.example.practicaltaskemarat.dao.repository

import com.example.practicaltaskemarat.dao.University
import com.example.practicaltaskemarat.dao.UniversityDao
//import com.example.practicaltaskemarat.network.University

import javax.inject.Singleton

@Singleton
class UniversityRepository(private val universityDao: UniversityDao) {

    suspend fun insert(university: University) {
        universityDao.insert(university)
    }
    suspend fun insertAll(universities: List<University>) {
        universityDao.insertAll(universities)
    }
    suspend fun getAllUniversities(): List<University> {
        return universityDao.getAllUniversities()
    }
    suspend fun isDatabaseEmpty(): Boolean {
        return universityDao.getAllUniversities().isEmpty()
    }
    suspend fun deleteAll() {
        universityDao.deleteAll()
    }
}
