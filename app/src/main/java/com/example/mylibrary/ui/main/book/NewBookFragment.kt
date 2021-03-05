package com.example.mylibrary.ui.main.book

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylibrary.data.network.ApiService
import com.example.mylibrary.data.network.ConnectivityInterceptor
import com.example.mylibrary.data.repository.BookRepository
import com.example.mylibrary.databinding.NewBookFragmentBinding
import com.example.mylibrary.ui.ViewModelFactory
import com.example.mylibrary.ui.main.adapter.BookAdapter

class NewBookFragment : Fragment() {

    companion object {
        fun newInstance() = NewBookFragment()
    }

    private lateinit var viewModel: NewBookViewModel
    private lateinit var bindings: NewBookFragmentBinding
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindings = NewBookFragmentBinding.inflate(inflater, container, false)
        bind()
        return bindings.root
    }

    private fun bind() {

        val connectivityInterceptor = ConnectivityInterceptor(this.requireContext())
        val api = ApiService(connectivityInterceptor)
        val repository = BookRepository(api)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(repository)
        ).get(NewBookViewModel::class.java)

        bindings.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BookAdapter()
        bindings.recyclerView.addItemDecoration(
            DividerItemDecoration(
                bindings.recyclerView.context,
                (bindings.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )



        bindings.recyclerView.adapter = adapter
        viewModel.init()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            adapter.setData(state.books)
        }

    }

}

