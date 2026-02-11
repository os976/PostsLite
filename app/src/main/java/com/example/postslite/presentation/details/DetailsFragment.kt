package com.example.postslite.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.postslite.R
import com.example.postslite.databinding.FragmentDetailsBinding
import com.example.postslite.domain.model.Post
import com.example.postslite.presentation.common.DateTimeUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val vm: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        binding.toolbar.title = "Post Details"
        binding.toolbar.subtitle = DateTimeUtils.nowFormatted()
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.title.text = args.title
        binding.body.text = args.body

        binding.btnSave.text = if (args.isSaved) "Unsave" else "Save"
        binding.btnSave.setOnClickListener {
            val post = Post(
                id = args.postId,
                title = args.title,
                body = args.body,
                isSaved = args.isSaved
            )
            vm.toggle(post)
        }

        // Auto update date & time every minute
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (isActive) {
                    binding.toolbar.subtitle = DateTimeUtils.nowFormatted()
                    delay(60_000)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
