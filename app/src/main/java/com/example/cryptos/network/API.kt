package com.example.cryptos.network

import com.example.cryptos.network.Remote.CryptosAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://api.coindesk.com"
private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

object API {
    val cryptosAPI: CryptosAPI by lazy {
        retrofit.create(CryptosAPI::class.java)
    }
}