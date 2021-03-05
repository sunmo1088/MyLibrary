package com.example.mylibrary.data.network.response


import com.google.gson.annotations.SerializedName

data class Book(
    val image: String,
    val isbn13: String,
    val price: String,
    val subtitle: String,
    val title: String,
    val url: String
)