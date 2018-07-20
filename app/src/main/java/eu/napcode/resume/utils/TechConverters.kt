package eu.napcode.resume.utils

import android.arch.persistence.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TechConverters {

    @TypeConverter
    fun fromString(value: String): Array<String>? {
        val listType = object : TypeToken<Array<String>>() {}.type

        return Gson().fromJson<Array<String>>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: Array<String>): String {
        val gson = Gson()

        return gson.toJson(list)
    }
}
