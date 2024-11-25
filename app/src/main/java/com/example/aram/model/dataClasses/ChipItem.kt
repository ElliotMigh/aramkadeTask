package com.example.aram.model.dataClasses


import com.google.gson.annotations.SerializedName

class ChipItem : ArrayList<ChipItem>(){
    data class ChipItemItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("titile")
        val titile: String
    )
}