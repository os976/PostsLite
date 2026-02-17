package com.example.postslite.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postslite.R
import com.example.postslite.databinding.FragmentPostsBinding
import com.example.postslite.presentation.common.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostsAdapter(
            onClick = { viewModel.onPostClicked(it) },
            onSaveClick = { viewModel.onToggleSave(it) }
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        binding.searchEdit.doAfterTextChanged {
            viewModel.onSearchChanged(it?.toString().orEmpty())
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.progress.visibility = View.GONE
            binding.emptyText.visibility = View.GONE
            binding.recycler.visibility = View.GONE

            when (state) {
                UiState.Loading -> binding.progress.visibility = View.VISIBLE
                UiState.Empty -> binding.emptyText.visibility = View.VISIBLE
                is UiState.Error -> {
                    binding.emptyText.visibility = View.VISIBLE
                    binding.emptyText.text = state.message
                }
                is UiState.Success -> {
                    binding.recycler.visibility = View.VISIBLE
                    adapter.submitList(state.data)
                }
            }
        }

        viewModel.openDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { post ->
                val bundle = Bundle().apply {
                    putInt("postId", post.id)
                    putString("title", post.title)
                    putString("body", post.body)
                    putBoolean("isSaved", post.isSaved)
                }
                findNavController().navigate(R.id.detailsFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
