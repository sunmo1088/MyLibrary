package com.example.mylibrary.ui.main.adapter

import android.content.Context
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mylibrary.R
import com.example.mylibrary.data.network.response.Book
import com.example.mylibrary.databinding.ItemNewBookBinding

typealias BookClicked = (Book) -> Unit

sealed class BookAdapterViewHolder<Binding: ViewDataBinding>(protected val bindings: Binding, protected val context: Context): RecyclerView.ViewHolder(bindings.root){

        abstract fun bind(item: BookAdapterItem)

        class BookItemViewHolder(
            binding: ItemNewBookBinding,
            context: Context
//            val bookClicked: BookClicked
        ): BookAdapterViewHolder<ItemNewBookBinding>(binding, context){
            override fun bind(item: BookAdapterItem) {
                bindings.apply {
                    textViewTitle.text = item.title
                    textViewSubtitle.text = item.subTitle
                    textViewIsbn.text = item.isbn
                    textViewPrice.text = item.price

                    Glide.with(context)
                        .load(item.image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView)

                    //root.setOnClickListener { bookClicked(item.book) }
                }
            }

        }
}

class BookAdapterItem(val book: Book){
    val title = book.title
    val subTitle = book.subtitle
    val isbn = book.isbn13
    val price = book.price
    val image = book.image
}

//