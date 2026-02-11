package com.example.postslite.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.postslite.R
import com.example.postslite.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val vm: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        binding.versionText.text = "Version 1.0"
        binding.aboutText.text = "PostsLite is a lightweight offline-first posts app optimized for legacy devices."

        binding.btnClearAll.setOnClickListener {
            vm.clearAllSaved()
        }

        binding.btnClearRecent.setOnClickListener {
            vm.clearRecent()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.events.collect { event ->
                    when (event) {
                        SettingsEvent.SavedCleared ->
                            Snackbar.make(binding.root, "All saved posts cleared", Snackbar.LENGTH_SHORT).show()

                        SettingsEvent.RecentCleared ->
                            Snackbar.make(binding.root, "Recent cleared", Snackbar.LENGTH_SHORT).show()

                        is SettingsEvent.Error ->
                            Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG).show()
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
