package com.example.postslite.presentation.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.postslite.R
import com.example.postslite.databinding.FragmentHomeTabsBinding
import com.example.postslite.presentation.posts.PostsFragment
import com.example.postslite.presentation.saved.SavedFragment
import com.example.postslite.presentation.tabs.recent.RecentFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeTabsFragment : Fragment(R.layout.fragment_home_tabs) {

    private var _binding: FragmentHomeTabsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeTabsBinding.bind(view)

        binding.toolbar.inflateMenu(R.menu.menu_home)

        binding.toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_settings) {
                findNavController().navigate(R.id.action_home_to_settings)
                true
            } else {
                false
            }
        }

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> PostsFragment()
                    1 -> SavedFragment()
                    else -> RecentFragment()
                }
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.text = when (pos) {
                0 -> "All"
                1 -> "Saved"
                else -> "Recent"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
