package com.example.myapplication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Database.Book
import com.example.myapplication.databinding.ItemBookBinding

class BookViewHolder(private val binding:ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(book: Book){
        with(binding){
            textViewNameAutor.text=book.nameAutor
            textViewNameBook.text=book.nameBook
        }
    }
}