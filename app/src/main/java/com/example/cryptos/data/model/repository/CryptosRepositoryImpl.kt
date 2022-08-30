package com.example.cryptos.data.model.repository

import com.example.cryptos.data.model.Bitcoin
import com.example.cryptos.domain.CryptosRepository
import com.example.cryptos.network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptosRepositoryImpl @Inject constructor(): CryptosRepository {

    override suspend fun getBitcoin(): Bitcoin =
        withContext(Dispatchers.IO) {
            val response = try {
                API.cryptosAPI.getBitcoinData().execute()
            } catch (e: Exception) {
                null
            }
            response?.body().let {
                return@withContext it!!
            }
        }
}