package com.example.cryptos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptos.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.viewModelScope.launch {
            val wow = viewModel.getBitcoint()
            Log.d("demo", wow.toString())


        }
    }
}