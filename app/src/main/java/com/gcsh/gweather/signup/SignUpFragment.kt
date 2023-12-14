package com.gcsh.gweather.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gcsh.gweather.R
import com.gcsh.gweather.common.UserPreferencesManager
import com.gcsh.gweather.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var userPreferencesManager: UserPreferencesManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)

        val fieldUserName = binding.editTextSignUpUsername
        val fieldPassword = binding.editTextSignUpPassword

        userPreferencesManager = UserPreferencesManager(requireContext())

        binding.btnSignUp.setOnClickListener {
            val inputUserName = fieldUserName.text.toString()
            val inputPassword = fieldPassword.text.toString()
            userPreferencesManager.saveUserData(inputUserName, inputPassword)
            Toast.makeText(requireContext(), "Signup complete...", Toast.LENGTH_SHORT)
                .show()

            findNavController().navigate(R.id.action_signUpFragment_to_landingFragment)
        }

        return binding.root
    }

}