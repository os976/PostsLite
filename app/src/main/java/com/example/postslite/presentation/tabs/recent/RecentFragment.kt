package com.example.postslite.presentation.tabs.recent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postslite.R
import com.example.postslite.databinding.FragmentRecentBinding
import com.example.postslite.domain.model.Post
import com.example.postslite.presentation.common.UiState
import com.example.postslite.presentation.posts.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentFragment : Fragment(R.layout.fragment_recent) {

    private var _binding: FragmentRecentBinding? = null
    private val binding get() = _binding!!

    private val vm: RecentViewModel by viewModels()
    private lateinit var adapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRecentBinding.bind(view)

        adapter = PostsAdapter(
            onClick = { openDetails(it) },
            onSaveClick = { vm.toggleSave(it) }
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { render(it) }
            }
        }
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
            putBoolean("isSaved", post.isSaved)
        }
        findNavController().navigate(R.id.detailsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
