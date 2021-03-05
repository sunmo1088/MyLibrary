package com.example.mylibrary.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.databinding.ItemNewBookBinding
import java.lang.Error

//private val bookClicked: BookClicked
class BookAdapter(): RecyclerView.Adapter<BookAdapterViewHolder<ViewDataBinding>>() {

    private val data: MutableList<BookAdapterItem> = mutableListOf()

    fun setData(data: List<BookAdapterItem>){
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BookAdapterViewHolder<ViewDataBinding>, position: Int) {
        holder.bind(data[position])
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookAdapterViewHolder<ViewDataBinding> =
        when(viewType){
            0 -> BookAdapterViewHolder.BookItemViewHolder(
                ItemNewBookBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                parent.context
                //bookClicked
            )
            else -> throw Error("Invalid view type")
        } as BookAdapterViewHolder<ViewDataBinding>
}
