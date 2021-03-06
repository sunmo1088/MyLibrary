package com.example.mylibrary.data.network

import com.example.mylibrary.data.network.response.Book
import com.example.mylibrary.data.network.response.BookResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("new")
    suspend fun getBooks() : BookResponse

    @GET("search/{query}")
    suspend fun search(@Path("query") query: String?) : BookResponse

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ApiService{

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.itbook.store/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}