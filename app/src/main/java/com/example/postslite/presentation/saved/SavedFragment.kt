package com.example.postslite.presentation.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val vm: SavedViewModel by viewModels()
    private lateinit var adapter: SavedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SavedAdapter(
            onSelectionChanged = { count ->
                binding.btnDelete.isEnabled = count > 0
            },
            onPostClick = { post ->
                vm.onPostClicked(post)
            }
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        binding.btnSelectAll.setOnClickListener { adapter.selectAll() }
        binding.btnClear.setOnClickListener { adapter.clearSelection() }

        binding.btnDelete.setOnClickListener {
            val selectedPosts = adapter.getSelectedPosts()
            if (selectedPosts.isNotEmpty()) showConfirmDelete(selectedPosts)
        }

        vm.savedPosts.observe(viewLifecycleOwner) { list ->
            binding.progress.visibility = View.GONE
            binding.errorText.visibility = View.GONE

            if (list.isNullOrEmpty()) {
                binding.emptyText.visibility = View.VISIBLE
                adapter.submitList(emptyList())
            } else {
                binding.emptyText.visibility = View.GONE
                adapter.submitList(list)
            }
        }

        vm.openDetails.observe(viewLifecycleOwner) { event ->
            val post = event.getContentIfNotHandled() ?: return@observe
            openDetails(post)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    vm.deleteEvent.collectLatest { deleted ->
                        val msg = if (deleted.size == 1) "1 item deleted" else "${deleted.size} items deleted"
                        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
                            .setAction("Undo") { vm.restore(deleted) }
                            .show()
                    }
                }
            }
        }
    }

    private fun showConfirmDelete(posts: List<Post>) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete saved posts?")
            .setMessage("Delete ${posts.size} item(s)?")
            .setPositiveButton("Delete") { _, _ ->
                vm.deletePosts(posts)
                adapter.clearSelection()
            }
            .setNegativeButton("Cancel", null)
            .show()
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
        binding.recycler.adapter = null
        _binding = null
        super.onDestroyView()
    }

}
