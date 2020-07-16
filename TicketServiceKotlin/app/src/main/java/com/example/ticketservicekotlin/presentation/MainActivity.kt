package com.example.ticketservicekotlin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticketservicekotlin.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity] is only responsible for setting the content view that contains the
 * Navigation Host.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}