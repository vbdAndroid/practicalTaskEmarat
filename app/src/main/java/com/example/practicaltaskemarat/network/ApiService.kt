package com.example.practicaltaskemarat.network


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.practicaltaskemarat.dao.University

interface ApiService {


//    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NTgyOTc4ZDU4MGQ2MzJlMzJjZWE0OTc0ODY3ODRmMCIsInN1YiI6IjY2NDRhOWZmMDQ3NTM1OGZmOTViY2RlOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3ME1lJYZv56CjsfNNtDRXHKbdzeQmNRgFqfSRoTeIdU")
    @GET("/search?country=United%20Arab%20Emirates")
    fun getListing():Call<List<University>>



}
