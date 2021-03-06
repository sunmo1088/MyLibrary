package com.example.mylibrary.data.repository

import com.example.mylibrary.data.network.ApiService
import com.example.mylibrary.data.network.response.Book
import com.example.mylibrary.data.network.response.BookResponse

class BookRepository(
    private val apiService: ApiService
) {

    suspend fun getBooks(): BookResponse {
        return apiService.getBooks()
    }

    suspend fun searchBook(query: String?) : BookResponse {
        return apiService.search(query)
    }
}