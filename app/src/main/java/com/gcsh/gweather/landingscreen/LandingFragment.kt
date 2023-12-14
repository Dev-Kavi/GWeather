package com.gcsh.gweather.landingscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gcsh.gweather.R
import com.gcsh.gweather.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLandingBinding.inflate(inflater, container, false)

        binding.btnLogIn.setOnClickListener {
            it.findNavController().navigate(R.id.action_landingFragment_to_logInFragment)
        }

        binding.btnSignUp.setOnClickListener {
            it.findNavController().navigate(R.id.action_landingFragment_to_signUpFragment)
        }

        return binding.root
    }

}