package com.example.aram.model.api

import android.util.Log
import com.example.aram.model.dataClasses.ChipItem
import com.example.aram.model.dataClasses.CourseItem
import com.example.aram.model.dataClasses.SessionItem
import com.example.aram.util.BASE_URL
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    private var apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getCategory(): List<ChipItem.ChipItemItem> {
        return apiService.getCategoryItem()
    }

    suspend fun getCourse(catId: Int): List<CourseItem.CourseItemItem> {
        return apiService.getCourseItem(catId)
    }

    suspend fun getCourseItemWithCatId(catId: Int): List<CourseItem.CourseItemItem> {
        return apiService.getCourseItem(catId)
    }

    suspend fun getSessionItem(itemId: Int): List<SessionItem.SessionItemItem> {
        return apiService.getSessionItem(itemId)
    }

}