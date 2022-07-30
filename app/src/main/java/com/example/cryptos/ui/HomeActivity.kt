package com.example.cryptos.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.cryptos.R
import com.example.cryptos.replaceFragment
import com.example.cryptos.showOptionsDialog
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val FAVORITE_CURRENCY_KEY = "FAVORITE_CURRENCY_KEY"
    private val viewModel: HomeActivityViewModel by viewModels()
    lateinit var bitCoinButton: Button
    lateinit var allCryptos: Button
    private var favoriteCurrency = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrenciesData()
        if (favoriteCurrencyIsSet()) setUpUi()
        else showCurrencyDialog()
    }

    private fun showCurrencyDialog() {
        showOptionsDialog(
            title ="Choose a currency",
            cancelable = false,
            items = arrayOf("USD", "GBP", "EUR"),
            selectedItemAction = { selectedItemIndex ->
                when (selectedItemIndex) {
                    0 -> favoriteCurrency = "USD"
                    1 -> favoriteCurrency = "GBP"
                    2 -> favoriteCurrency = "EUR"
                }
                viewModel.favoriteCurrency = favoriteCurrency
                saveFavoriteCurrency()
                setUpUi()
            }
        )
    }

    private fun setUpUi() {

        bitCoinButton = findViewById(R.id.bitcoint_button)
        allCryptos = findViewById(R.id.all_cryptos_button)
        bitCoinButton.setOnClickListener(){ replaceFragment<BitCoinFragment>() }
        allCryptos.setOnClickListener(){ replaceFragment<AllCryptosFragment>() }
    }

    private fun getCurrenciesData() {
        viewModel.viewModelScope.launch {
            viewModel.getBitcoint()
            viewModel.getAllCryptos()
        }
    }

    private fun getFavoriteCurrency() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(FAVORITE_CURRENCY_KEY, favoriteCurrency)
            favoriteCurrency = sharedPref.getString(FAVORITE_CURRENCY_KEY,"").toString()
            viewModel.favoriteCurrency = favoriteCurrency
            apply()
        }
    }

    private fun saveFavoriteCurrency() {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(FAVORITE_CURRENCY_KEY, favoriteCurrency)
            apply()
        }
    }

    private fun favoriteCurrencyIsSet(): Boolean {
        getFavoriteCurrency()
        return favoriteCurrency.isNotBlank()
    }
}