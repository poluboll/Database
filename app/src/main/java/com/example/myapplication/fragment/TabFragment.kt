package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.room.Room
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.Database.BookDatabase
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTabBinding
import com.google.android.material.tabs.TabLayoutMediator


class TabFragment : Fragment() {
    private var _binding: FragmentTabBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTabBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewPager.adapter = FragmentTabAdapter(this@TabFragment)

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = position.toString()
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class FragmentTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ListFragment()
            1 -> InputDataFragment()
            else -> throw IndexOutOfBoundsException("wrong position $position ")
        }
    }
}