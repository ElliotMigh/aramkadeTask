package com.example.aram.model.dataClasses


import com.google.gson.annotations.SerializedName

class CourseItem : ArrayList<CourseItem>(){
    data class CourseItemItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("image2")
        val image2: String,
        @SerializedName("sessions")
        val sessions: String,
        @SerializedName("titile")
        val titile: String
    )
}