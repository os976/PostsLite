package com.example.postslite.presentation.tabs

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbarTitle()
        setupToolbarMenu()
        setupTabs()
    }

    private fun setupToolbarTitle() {
        val toolbar = binding.toolbar

        toolbar.title = ""
        toolbar.logo = null

        toolbar.setContentInsetsRelative(16, 16)

        val existing = toolbar.findViewById<View?>(R.id.titleLayout)
        if (existing != null) toolbar.removeView(existing)

        val titleView = layoutInflater.inflate(R.layout.toolbar_title, toolbar, false)

        val params = androidx.appcompat.widget.Toolbar.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
        }

        toolbar.addView(titleView, params)
    }

    private fun setupToolbarMenu() {
        val toolbar = binding.toolbar
        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.menu_home)

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    findNavController().navigate(R.id.settingsFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupTabs() {
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
