package com.gcsh.gweather.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gcsh.gweather.common.UserPreferencesManager
import com.gcsh.gweather.databinding.FragmentLogInBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LogInFragment : Fragment() {

    private var loginResultJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLogInBinding.inflate(inflater, container, false)
        setupUI(binding)
        return binding.root
    }

    private fun setupUI(binding: FragmentLogInBinding) {
        val fieldUserName = binding.editTextLoginUsername
        val fieldPassword = binding.editTextLoginPassword

        val userPreferencesManager = UserPreferencesManager(requireContext())
        val viewModel: LogInViewModel by viewModels()

        binding.btnLogin.setOnClickListener {
            val inputUserName = fieldUserName.text.toString()
            val inputPassword = fieldPassword.text.toString()

            userPreferencesManager.run {
                val savedUserName = getUsername()
                val savedPassword = getPassword()

                if (savedUserName != null && savedPassword != null) {
                    viewModel.loginUser(inputUserName, inputPassword, savedUserName, savedPassword)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No saved credentials available.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }

        observeLoginResult(viewModel)
    }

    private fun observeLoginResult(viewModel: LogInViewModel) {
        loginResultJob?.cancel()
        loginResultJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginResult.collect { result ->
                when (result) {
                    is LoginResult.Success -> {
                        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT)
                            .show()
                        // TODO: Navigate to the home screen
                    }

                    is LoginResult.Failure -> {
                        Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                    }

                    LoginResult.Initial -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        loginResultJob?.cancel() // Cancel the job when the view is destroyed
        super.onDestroyView()
    }

}