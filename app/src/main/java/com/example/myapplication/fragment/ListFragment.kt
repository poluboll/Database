package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import com.example.myapplication.Database.Book
import com.example.myapplication.Database.BookDatabase
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.BookAdapter
import com.example.myapplication.databinding.FragmentListBinding
import kotlinx.coroutines.flow.flowOf


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private var adapter = BookAdapter()

    override fun onResume() {
        super.onResume()
        adapter.setBooks(loadBooks())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(
                view.context
            )
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadBooks(): List<Book>{
        return (requireActivity() as MainActivity).database.bookDao().getAll()
    }
}