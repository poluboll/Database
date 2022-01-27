package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.myapplication.Database.Book
import com.example.myapplication.Database.BookDatabase
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInputDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InputDataFragment : Fragment() {

    private var _binding: FragmentInputDataBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentInputDataBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonAddBook.setOnClickListener {
                val nameAutorStr = editTextNameAutor.text.toString()
                val nameBookStr = editTextNameBook.text.toString()

                if (nameAutorStr == "" || nameBookStr == "") {
                    Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                } else {
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                        createBook(nameAutorStr, nameBookStr)
                    }
                    Toast.makeText(context, "Книга добавлена", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun createBook(nameAutorStr: String, nameBookStr: String) {
        (requireActivity() as MainActivity).database.bookDao().insertAll(
            Book(nameAutor = nameAutorStr, nameBook = nameBookStr))
    }
}