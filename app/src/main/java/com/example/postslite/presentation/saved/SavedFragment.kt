package com.example.postslite.presentation.saved

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postslite.R
import com.example.postslite.databinding.FragmentSavedBinding
import com.example.postslite.presentation.common.DateTimeUtils
import com.example.postslite.presentation.common.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved) {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val vm: SavedViewModel by viewModels()
    private lateinit var adapter: SavedAdapter

    private var selectAllItem: MenuItem? = null
    private var clearItem: MenuItem? = null
    private var deleteItem: MenuItem? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)

        // Toolbar
        binding.toolbar.title = "Saved Posts"
        binding.toolbar.subtitle = DateTimeUtils.nowFormatted()

        binding.toolbar.setNavigationIcon(
            androidx.appcompat.R.drawable.abc_ic_ab_back_material
        )
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Menu بدون Icons (Text فقط)
        binding.toolbar.menu.clear()

        selectAllItem = binding.toolbar.menu.add("Select All").apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            isVisible = false
        }

        clearItem = binding.toolbar.menu.add("Clear").apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            isVisible = false
            isEnabled = false
        }

        deleteItem = binding.toolbar.menu.add("Delete").apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            isVisible = false
            isEnabled = false
        }

        // Recycler
        adapter = SavedAdapter { count ->
            val hasSelection = count > 0
            deleteItem?.isEnabled = hasSelection
            clearItem?.isEnabled = hasSelection

            binding.toolbar.title =
                if (hasSelection) "Selected: $count" else "Saved Posts"
        }

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        // Menu Clicks
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item) {
                selectAllItem -> {
                    adapter.selectAll()
                    true
                }

                clearItem -> {
                    adapter.clearSelection()
                    true
                }

                deleteItem -> {
                    val ids = adapter.getSelectedIds()
                    if (ids.isEmpty()) return@setOnMenuItemClickListener true

                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete posts?")
                        .setMessage("Are you sure you want to delete selected posts?")
                        .setPositiveButton("Delete") { _, _ ->
                            vm.deleteSelected(ids)
                            adapter.clearSelection()
                        }
                        .setNegativeButton("Cancel", null)
                        .show()

                    true
                }

                else -> false
            }
        }

        // Observe State
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                vm.state.collect { state ->
                    binding.progress.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE

                    when (state) {

                        UiState.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                            setActionsVisible(false)
                        }

                        UiState.Empty -> {
                            binding.emptyText.visibility = View.VISIBLE
                            adapter.submitList(emptyList())
                            setActionsVisible(false)
                        }

                        is UiState.Success -> {
                            adapter.submitList(state.data)
                            setActionsVisible(state.data.isNotEmpty())
                        }

                        is UiState.Error -> {
                            binding.emptyText.text = state.message
                            binding.emptyText.visibility = View.VISIBLE
                            setActionsVisible(false)
                        }
                    }
                }
            }
        }

        // Auto Update Time Every Minute
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (isActive) {
                    binding.toolbar.subtitle = DateTimeUtils.nowFormatted()
                    delay(60_000)
                }
            }
        }
    }

    private fun setActionsVisible(visible: Boolean) {
        selectAllItem?.isVisible = visible
        clearItem?.isVisible = visible
        deleteItem?.isVisible = visible

        if (!visible) {
            clearItem?.isEnabled = false
            deleteItem?.isEnabled = false
            binding.toolbar.title = "Saved Posts"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
