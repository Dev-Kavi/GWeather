package com.gcsh.gweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var initialNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        initialNavController = findNavController(R.id.initialLoadContainerView)
        return initialNavController.navigateUp() || super.onSupportNavigateUp()
    }
}