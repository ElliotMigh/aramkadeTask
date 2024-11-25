package com.example.aram.model.dataClasses


import com.google.gson.annotations.SerializedName

class SessionItem : ArrayList<SessionItem>(){
    data class SessionItemItem(
        @SerializedName("free")
        val free: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("room_id")
        val roomId: Int,
        @SerializedName("session")
        val session: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("url")
        val url: String
    )
}