package com.example.cryptos.ui

import androidx.lifecycle.ViewModel
import com.example.cryptos.network.Repository.CryptosRepository

class HomeActivityViewModel: ViewModel() {

    suspend fun getBitcoint() = CryptosRepository.getBitcoint()
    suspend fun getAllCryptos() = CryptosRepository.getALlCryptos()
}