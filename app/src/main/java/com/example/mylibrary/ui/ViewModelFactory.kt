package com.example.mylibrary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.data.repository.BookRepository
import com.example.mylibrary.ui.main.book.NewBookViewModel

class ViewModelFactory(
    private val repository: BookRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewBookViewModel::class.java)) {
            return NewBookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}