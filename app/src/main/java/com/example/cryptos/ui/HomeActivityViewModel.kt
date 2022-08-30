package com.example.cryptos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class HomeActivityViewModel @Inject constructor(private val repository: com.example.cryptos.domain.CryptosRepository): ViewModel() {

    private val bitcoin: MutableLiveData<Resources<Bitcoin>> = MutableLiveData()
    val bitCoin: LiveData<Resources<Bitcoin>> = bitcoin

    private val _allCryptos = MutableLiveData<Resources<List<AllCryptosItem>>>()
    var allCryptos: LiveData<Resources<List<AllCryptosItem>>> = _allCryptos
    private var minutesToUpdateBitCoin = 300000L
    var favoriteCurrency = ""

    suspend fun getBitcoin() {
        viewModelScope.launch {
             repository.getBitcoin().collect {
                 bitcoin.postValue(it)
            }
        }
    }

    suspend fun getAllCryptos() {
        viewModelScope.launch {
            repository.getAllCryptos().collect {
                _allCryptos.postValue(it)
            }
        }
    }

    fun updateBitCoinAutomatically() {
        CoroutineScope(Dispatchers.IO).launch {
             repository.getBitcoin()
            delay(minutesToUpdateBitCoin)
            getBitcoin()
            updateBitCoinAutomatically()
        }
    }
}