package uz.direction.coroutineshomework

import com.google.gson.annotations.SerializedName

data class Meme(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)
