package com.example.mylibrary.ui.main.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.data.repository.BookRepository
import com.example.mylibrary.ui.main.adapter.BookAdapterItem
import com.example.mylibrary.utils.ProgressStatus
import com.example.mylibrary.utils.suspendPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal data class State(
    val books: List<BookAdapterItem> = emptyList(),
    val searchedBook : List<BookAdapterItem> = emptyList(),
    val progressStatus: ProgressStatus = ProgressStatus.LOADING
)

class NewBookViewModel(private val bookRepository: BookRepository) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State())
    internal val state: LiveData<State> = _state

    fun init() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val response = bookRepository.getBooks()
                val bookList = response.books
                val books = bookList.map { BookAdapterItem(it) }

                _state.suspendPost(
                    _state.value?.copy(
                        books = books,
                        progressStatus = ProgressStatus.SUCCESS
                    )
                )
            } catch (e: Throwable) {
                withContext(Dispatchers.Main){
                    _state.value?.copy(
                        progressStatus = ProgressStatus.ERROR
                    )
                }
            }

        }
    }

    fun search(query : String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = bookRepository.searchBook(query)
                val bookList = response.books
                val searchedBooks = bookList.map { BookAdapterItem(it) }

                _state.suspendPost(
                    _state.value?.copy(
                        searchedBook = searchedBooks,
                        progressStatus = ProgressStatus.SUCCESS
                    )
                )

            } catch (e: Throwable) {
                withContext(Dispatchers.Main){
                    _state.value?.copy(
                        progressStatus = ProgressStatus.ERROR
                    )
                }
            }
        }
    }

}

