package com.example.cryptos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptos.R
import com.example.cryptos.addFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeActivityViewModel by viewModels()
    lateinit var bitCointButton: Button
    lateinit var allCryptos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.viewModelScope.launch {
            viewModel.getBitcoint()
            val wow = viewModel.getAllCryptos()

            Log.d("demo11", wow?.size.toString())

        }

        viewModel.bitCoin.observe(this) {
            Log.d("demo", it.toString())
        }

        setUpUi()
    }

    private fun setUpUi() {
        bitCointButton = findViewById(R.id.bitcoint_button)
        allCryptos = findViewById(R.id.all_cryptos_button)

        bitCointButton.setOnClickListener(){ addFragment<BitCoinFragment>() }
        allCryptos.setOnClickListener(){ addFragment<AllCryptosFragment>() }
    }
}