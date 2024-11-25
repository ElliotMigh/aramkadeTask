package com.example.aram.model.api

import android.telecom.Call
import com.example.aram.model.dataClasses.ChipItem
import com.example.aram.model.dataClasses.CourseItem
import com.example.aram.model.dataClasses.SessionItem
import com.example.aram.util.BASE_URL_CATEGORY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // برای استفاده از Coroutines باید از suspend استفاده کنید.
    @GET("meditation_cat.php")
    suspend fun getCategoryItem(): List<ChipItem.ChipItemItem>

    @GET("meditation_item.php")
    suspend fun getCourseItem(
        @Query("cat_id") cat_id: Int = 1
    ): List<CourseItem.CourseItemItem>

    @GET("meditation_session.php")
    suspend fun getSessionItem(
        @Query("item_id") item_id: Int
    ): List<SessionItem.SessionItemItem>

}