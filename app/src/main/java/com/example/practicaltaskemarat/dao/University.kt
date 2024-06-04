package com.example.practicaltaskemarat.dao


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "university_table")
data class University(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val alpha_two_code: String,
    val domains: List<String>,
    val name: String,
    val web_pages: List<String>,
    val country: String,
    @SerializedName("state-province") val state_province: String?
) : Parcelable


class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }
}