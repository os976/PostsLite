package com.example.postslite.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.postslite.R
import com.example.postslite.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val vm: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        binding.versionText.text = "Version 1.0"

        binding.aboutText.text =
            "PostsLite is a lightweight offline-first posts app optimized for legacy devices."

        binding.btnClearAll.setOnClickListener {
            vm.clearAllSaved()
            Snackbar.make(binding.root, "All saved posts cleared", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnClearRecent.setOnClickListener {
            vm.clearRecent()
            Snackbar.make(binding.root, "Recent cleared", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
