package com.example.cryptos.network.Repository

import com.example.cryptos.network.API
import com.example.cryptos.network.API2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CryptosRepository {
   suspend fun getBitcoint() = withContext(Dispatchers.IO) {

       val response = try {
           API.cryptosAPI.getBitcoinData().execute()
       } catch (e: Exception) {
           null
       }
       response?.body().let {
           return@withContext it
       }
   }

   suspend fun getALlCryptos() = withContext(Dispatchers.IO) {
       val response = try {
           API2.cryptosAPI.getAllCryptosData().execute()
       } catch (e: Exception) {
           null
       }
       response?.body().let {
           return@withContext it
       }
   }
}