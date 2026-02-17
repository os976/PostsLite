package com.example.postslite.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.postslite.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val vm: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.versionText.text = "Version 1.0"
        binding.aboutText.text = "PostsLite is a lightweight offline-first posts app optimized for legacy devices."

        binding.btnClearAll.setOnClickListener { vm.clearAllSaved() }
        binding.btnClearRecent.setOnClickListener { vm.clearRecent() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    vm.events.collectLatest { event ->
                        when (event) {
                            is SettingsEvent.DeletedSaved -> {
                                val count = event.posts.size
                                val msg = if (count == 1) "1 saved item cleared" else "$count saved items cleared"
                                Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
                                    .setAction("Undo") { vm.undoSaved(event.posts) }
                                    .show()
                            }
                            is SettingsEvent.DeletedRecent -> {
                                val count = event.posts.size
                                val msg = if (count == 1) "1 recent item cleared" else "$count recent items cleared"
                                Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
                                    .setAction("Undo") { vm.undoRecent(event.posts) }
                                    .show()
                            }
                            is SettingsEvent.Error -> {
                                Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
