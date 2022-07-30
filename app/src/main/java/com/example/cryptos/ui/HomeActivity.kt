package com.example.cryptos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.cryptos.R
import com.example.cryptos.replaceFragment
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeActivityViewModel by viewModels()
    lateinit var bitCointButton: Button
    lateinit var allCryptos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.viewModelScope.launch {
            viewModel.getBitcoint()
            viewModel.getAllCryptos()
        }
        viewModel.bitCoin.observe(this) {}
        setUpUi()
    }

    private fun setUpUi() {
        bitCointButton = findViewById(R.id.bitcoint_button)
        allCryptos = findViewById(R.id.all_cryptos_button)

        bitCointButton.setOnClickListener(){ replaceFragment<BitCoinFragment>() }
        allCryptos.setOnClickListener(){ replaceFragment<AllCryptosFragment>() }
    }
}