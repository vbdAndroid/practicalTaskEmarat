package com.example.practicaltaskemarat.mvvm.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltaskemarat.MyApplication
import com.example.practicaltaskemarat.dao.repository.UniversityRepository
import com.example.practicaltaskemarat.network.ApiService
//import com.example.practicaltaskemarat.network.University
import com.example.practicaltaskemarat.dao.University
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ListingFragmentVM @Inject constructor(
    val myApplication: MyApplication, private val apiService: ApiService,
    private val repository: UniversityRepository
//    networkStateData: NetworkStatusHelper,
) : ViewModel() {


    var universityArrayList: List<University>? = null

    //    var moviesAdapter: ListingAdapter? = null
    var universityArrayListResposne = MutableLiveData<List<University>>()

    private val _universities = MutableLiveData<List<University>>()
    val universities: LiveData<List<University>> get() = _universities
    var universityDataCount = 0

    fun insert(university: University) = viewModelScope.launch {
        repository.insert(university)
    }

    fun insertAll(universities: List<University>) = viewModelScope.launch {
        repository.insertAll(universities)
    }

    fun getAllUniversities() = viewModelScope.launch {
        _universities.value = repository.getAllUniversities()
        universityDataCount = repository.getAllUniversities().size
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getListing() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (repository.isDatabaseEmpty()) {
                    Log.d("","DATA load from network")
                    val userProfileList = apiService.getListing()
                    userProfileList.enqueue(object : Callback<List<University>> {
                        override fun onResponse(
                            call: Call<List<University>>,
                            response: Response<List<University>>
                        ) {
                            if (response.isSuccessful) {
                                val universities = response.body()
                                universityArrayList = universities
                                universityArrayListResposne.postValue(universityArrayList)
                                universities?.let { insertAll(it) }

                            } else {
                                Log.e("API", "Failed to fetch popular movies: ${response.code()}")
                            }

                        }

                        override fun onFailure(call: Call<List<University>>, t: Throwable) {
                            Log.e("API", "Failed to fetch popular movies", t)
                        }
                    })
                } else {
                    Log.d("","DATA load from storage")
//                    _universities.value = repository.getAllUniversities()
                    universityArrayListResposne.postValue(repository.getAllUniversities())

                }

            } catch (e: Exception) {
                Log.e("API", "Failed to fetch popular movies", e)
            }

        }
    }
}