package com.gcsh.gweather.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gcsh.gweather.R
import com.gcsh.gweather.common.UserPreferencesManager
import com.gcsh.gweather.databinding.FragmentLogInBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LogInFragment : Fragment() {

    private var loginResultJob: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
                    showToast("No saved credentials available.")
                }
            }
        }

        observeLoginResult(viewModel)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun observeLoginResult(viewModel: LogInViewModel) {
        loginResultJob?.cancel()
        loginResultJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginResult.collect { result ->
                handleLoginResult(result)
            }
        }
    }

    private fun handleLoginResult(result: LoginResult) {
        when (result) {
            is LoginResult.Success -> {
                showToast("Login successful")
                findNavController().navigate(R.id.action_logInFragment_to_homeWeatherActivity)
            }

            is LoginResult.Failure -> showToast(result.error)
            LoginResult.Initial -> { /* Do nothing for initial state */
            }
        }
    }

    override fun onDestroyView() {
        loginResultJob?.cancel()
        super.onDestroyView()
    }

}