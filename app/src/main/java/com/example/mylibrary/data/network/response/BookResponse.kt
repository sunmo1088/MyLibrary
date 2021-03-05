package com.example.mylibrary.data.network.response

import com.example.mylibrary.data.network.response.Book


data class BookResponse(
    val books: List<Book>,
    val error: String,
    val total: String
)