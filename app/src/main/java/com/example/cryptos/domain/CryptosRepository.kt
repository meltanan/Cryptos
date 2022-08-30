package com.example.cryptos.domain

import com.example.cryptos.data.model.AllCryptosItem
import com.example.cryptos.data.model.Bitcoin
import com.example.cryptos.util.Resources
import kotlinx.coroutines.flow.Flow

interface CryptosRepository {
    suspend fun getBitcoin(): Resources<Bitcoin>
    suspend fun getAllCryptos(): List<AllCryptosItem>
}