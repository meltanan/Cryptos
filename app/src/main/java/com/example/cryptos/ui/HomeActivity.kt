package com.example.cryptos.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.cryptos.R
import com.example.cryptos.replaceFragment
import com.example.cryptos.showOptionsDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val FAVORITE_CURRENCY_KEY = "FAVORITE_CURRENCY_KEY"
    lateinit var welcomeTextView: TextView
    private val viewModel: HomeActivityViewModel by viewModels()
    lateinit var bitCoinButton: Button
    lateinit var allCryptos: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrenciesData()
        if (favoriteCurrencyIsSet()) setUpUi()
        else showCurrencyDialog()
    }

    private fun showCurrencyDialog() {
        showOptionsDialog(
            title ="Choose a currency please",
            cancelable = false,
            items = arrayOf("USD", "GBP", "EUR"),
            selectedItemAction = { selectedItemIndex ->
                when (selectedItemIndex) {
                    0 -> viewModel.favoriteCurrency = "USD"
                    1 -> viewModel.favoriteCurrency = "GBP"
                    2 -> viewModel.favoriteCurrency = "EUR"
                }
                saveFavoriteCurrency()
                setUpUi()
            }
        )
    }

    private fun setUpUi() {
        bitCoinButton = findViewById(R.id.bitcoint_button)
        allCryptos = findViewById(R.id.all_cryptos_button)
        welcomeTextView = findViewById(R.id.welcome_textView)
        bitCoinButton.setOnClickListener(){
            replaceFragment<BitCoinFragment>()
            welcomeTextView.visibility = View.GONE
        }

        allCryptos.setOnClickListener(){
            replaceFragment<AllCryptosFragment>()
            welcomeTextView.visibility = View.GONE
        }
    }

    private fun getCurrenciesData() {
        viewModel.viewModelScope.launch {
            viewModel.getBitcoin()
            viewModel.getAllCryptos()
        }
        viewModel.updateBitCoinAutomatically()
    }

    private fun getFavoriteCurrency() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            viewModel.favoriteCurrency = sharedPref.getString(FAVORITE_CURRENCY_KEY,"").toString()
            apply()
        }
    }

    private fun saveFavoriteCurrency() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(FAVORITE_CURRENCY_KEY, viewModel.favoriteCurrency)
            apply()
        }
    }

    private fun favoriteCurrencyIsSet(): Boolean {
        getFavoriteCurrency()
        return viewModel.favoriteCurrency.isNotBlank()
    }

    override fun onBackPressed() {}
}