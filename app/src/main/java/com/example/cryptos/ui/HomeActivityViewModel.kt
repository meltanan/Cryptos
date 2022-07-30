package com.example.cryptos.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptos.data.model.AllCryptosItem
import com.example.cryptos.data.model.Bitcoin
import com.example.cryptos.network.Repository.CryptosRepository

class HomeActivityViewModel: ViewModel() {

    var bitCoin = MutableLiveData<Bitcoin>()
    var allCryptos = MutableLiveData<List<AllCryptosItem>>()
    var favoriteCurrency = ""

    suspend fun getBitcoint() {
        bitCoin.postValue(CryptosRepository.getBitcoint())
    }
    suspend fun getAllCryptos() {
        allCryptos.postValue(CryptosRepository.getALlCryptos())
    }

}