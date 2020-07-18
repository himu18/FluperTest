package com.personal.flupertest.database.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.personal.flupertest.database.db_model.Stores

class StoresConverters {
    @TypeConverter
    fun fromString(value: String): List<Stores> {
        val listType = object : TypeToken<List<Stores>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromString(value: List<Stores>): String {
        val gson = Gson()
        return gson.toJson(value)

    }
}