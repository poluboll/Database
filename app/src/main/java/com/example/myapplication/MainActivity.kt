package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.commit
import androidx.room.Room
import com.example.myapplication.Database.BookDatabase
import com.example.myapplication.fragment.TabFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val database: BookDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java,
            "book-db"
        ).allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.container, TabFragment())
                addToBackStack(null)
            }
        }
    }


}