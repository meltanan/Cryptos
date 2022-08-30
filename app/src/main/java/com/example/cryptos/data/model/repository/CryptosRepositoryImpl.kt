package com.example.cryptos.data.model.repository

import com.example.cryptos.data.model.AllCryptosItem
import com.example.cryptos.data.model.Bitcoin
import com.example.cryptos.domain.CryptosRepository
import com.example.cryptos.network.API
import com.example.cryptos.network.API2
import com.example.cryptos.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptosRepositoryImpl @Inject constructor(): CryptosRepository {

    override suspend fun getBitcoin(): Flow<Resources<Bitcoin>> = flow {
        emit(Resources.Loading())
            val response = try {
                API.cryptosAPI.getBitcoinData().execute()
            } catch (e: Exception) {
                null
            }
            response?.body().let {
                emit(Resources.Success(it))
            }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllCryptos(): Flow <Resources<List<AllCryptosItem>>> = flow {
        emit(Resources.Loading())
        val response = try {
            API2.cryptosAPI.getAllCryptosData().execute()
        } catch (e: Exception) {
            null
        }
        response?.body().let {
            emit(Resources.Success(it))
        }
    }.flowOn(Dispatchers.IO)
}