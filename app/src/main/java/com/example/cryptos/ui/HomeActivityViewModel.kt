package com.example.cryptos.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptos.data.model.AllCryptosItem
import com.example.cryptos.data.model.Bitcoin
import com.example.cryptos.network.Repository.CryptosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeActivityViewModel: ViewModel() {

    var bitCoin = MutableLiveData<Bitcoin>()
    var allCryptos = MutableLiveData<List<AllCryptosItem>>()
    private var minutesToUpdateBitCoin = 300000L
    var favoriteCurrency = ""

    suspend fun getBitcoin() { bitCoin.postValue(CryptosRepository.getBitcoint()) }
    suspend fun getAllCryptos() { allCryptos.postValue(CryptosRepository.getALlCryptos()) }

    fun updateBitCoinAutomatically() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(minutesToUpdateBitCoin)
            getBitcoin()
            updateBitCoinAutomatically()
        }
    }
}