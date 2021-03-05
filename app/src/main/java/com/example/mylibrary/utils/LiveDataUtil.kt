package com.example.mylibrary.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> MutableLiveData<T>.suspendPost(value: T?){
    withContext(Dispatchers.Main) {
        if (value != null) {
            this@suspendPost.value = value
        }
    }
}