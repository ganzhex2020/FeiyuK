package com.phi.feiyuk.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.phi.feiyuk.model.entity.MyEntity

/**
 *Author:ganzhe
 *时间:2020/11/12 11:16
 *描述:This is TypeConverters
 */
class ListConverters {

    @TypeConverter
    fun stringToObject(value: String): List<List<MyEntity>> {
        val listType = object : TypeToken<List<List<MyEntity>>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<List<MyEntity>>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}