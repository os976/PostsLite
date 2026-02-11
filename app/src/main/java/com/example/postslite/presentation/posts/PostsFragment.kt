package com.example.postslite.presentation.posts

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postslite.R
import com.example.postslite.databinding.FragmentPostsBinding
import com.example.postslite.domain.model.Post
import com.example.postslite.presentation.common.DateTimeUtils
import com.example.postslite.presentation.common.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsFragment : Fragment(R.layout.fragment_posts) {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val vm: PostsViewModel by viewModels()
    private lateinit var adapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPostsBinding.bind(view)

        binding.toolbar.title = "All Posts"
        binding.toolbar.subtitle = DateTimeUtils.nowFormatted()

        adapter = PostsAdapter(
            onClick = { openDetails(it) },
            onSaveClick = { vm.onToggleSave(it) }
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        binding.searchEdit.doAfterTextChanged {
            vm.onSearchChanged(it?.toString().orEmpty())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { render(it) }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (isActive) {
                    binding.toolbar.subtitle = DateTimeUtils.nowFormatted()
                    delay(60_000)
                }
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
        vm.onOpened(post)

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
