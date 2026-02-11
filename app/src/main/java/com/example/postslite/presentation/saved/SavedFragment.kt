package com.example.postslite.presentation.saved

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postslite.R
import com.example.postslite.databinding.FragmentSavedBinding
import com.example.postslite.domain.model.Post
import com.example.postslite.presentation.common.DateTimeUtils
import com.example.postslite.presentation.common.UiState
import com.google.android.material.snackbar.Snackbar
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)

        binding.toolbar.title = ""
        binding.toolbar.subtitle = DateTimeUtils.timeOnly()

        adapter = SavedAdapter(
            onSelectionChanged = { count ->
                binding.btnDelete.isEnabled = count > 0
            },
            onPostClick = { post ->
                openDetails(post)
            }
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        binding.btnSelectAll.setOnClickListener { adapter.selectAll() }
        binding.btnClear.setOnClickListener { adapter.clearSelection() }

        binding.btnDelete.setOnClickListener {
            val ids = adapter.getSelectedIds()
            if (ids.isEmpty()) return@setOnClickListener
            showConfirmDelete(ids)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { render(it) }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (isActive) {
                    binding.toolbar.subtitle = DateTimeUtils.timeOnly()
                    delay(60_000)
                }
            }
        }
    }

    private fun showConfirmDelete(ids: List<Int>) {
        val snapshot = adapter.currentList.filter { ids.contains(it.id) }

        AlertDialog.Builder(requireContext())
            .setTitle("Delete saved posts?")
            .setMessage("Delete ${ids.size} item(s)?")
            .setPositiveButton("Delete") { _, _ ->
                vm.deleteSelected(ids)
                adapter.clearSelection()

                Snackbar.make(binding.root, "Deleted ${ids.size} post(s)", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        vm.restore(snapshot)
                        Snackbar.make(binding.root, "Restored", Snackbar.LENGTH_SHORT).show()
                    }
                    .show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun render(state: UiState<List<Post>>) {
        binding.progress.visibility = View.GONE
        binding.emptyText.visibility = View.GONE
        binding.errorText.visibility = View.GONE

        when (state) {
            UiState.Loading -> binding.progress.visibility = View.VISIBLE
            UiState.Empty -> binding.emptyText.visibility = View.VISIBLE
            is UiState.Error -> {
                binding.errorText.text = state.message
                binding.errorText.visibility = View.VISIBLE
            }
            is UiState.Success -> adapter.submitList(state.data)
        }
    }

    private fun openDetails(post: Post) {
        val bundle = Bundle().apply {
            putInt("postId", post.id)
            putString("title", post.title)
            putString("body", post.body)
            putBoolean("isSaved", true)
        }
        findNavController().navigate(R.id.detailsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
