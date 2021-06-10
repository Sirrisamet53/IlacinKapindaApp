package com.h5190047.ilacinkapinda.util

import com.google.gson.Gson
import com.h5190047.ilacinkapinda.data.model.Product

object ObjeUtil {

    fun <T> objeToJsonString(objectData: T): String {
        val gson = Gson()
        return gson.toJson(objectData)
    }

    inline fun <reified T> jsonStringToObje(jsonString: String): T {
        val gson = Gson()
        return gson.fromJson(jsonString, T::class.java)
    }
//
//    fun objeToJsonString(liste: List<Product>?): String? {
//        val gson = Gson()
//        return gson.toJson(liste)
//    }

//    fun jsonStringToObje(jsonString: String?): List<Product>? {
//        val gson = Gson()
//        return gson.fromJson(jsonString , Array<Product>::class.java).toList()
//    }
}