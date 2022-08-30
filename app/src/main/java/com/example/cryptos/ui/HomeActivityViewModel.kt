package com.example.cryptos.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptos.data.model.AllCryptosItem
import com.example.cryptos.data.model.Bitcoin
import com.example.cryptos.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(val repository2: com.example.cryptos.domain.CryptosRepository): ViewModel() {

    var bitCoin = MutableLiveData<Bitcoin?>()
    var allCryptos = MutableLiveData<List<AllCryptosItem>>()
    private var minutesToUpdateBitCoin = 300000L
    var favoriteCurrency = ""

    suspend fun getBitcoin() {
        val response = repository2.getBitcoin()
        when (response) {
            is Resources.Success -> {
                    bitCoin.postValue(response.data)

            }
        }
    }
    suspend fun getAllCryptos() { allCryptos.postValue(repository2.getAllCryptos()) }

    fun updateBitCoinAutomatically() {
        CoroutineScope(Dispatchers.IO).launch {
            val wow = repository2.getBitcoin()
            Log.d("demo", wow.toString())
            delay(minutesToUpdateBitCoin)
            getBitcoin()
            updateBitCoinAutomatically()
        }
    }
}