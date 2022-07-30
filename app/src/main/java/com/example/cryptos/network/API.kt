package com.example.cryptos.network

import com.example.cryptos.network.Remote.CryptosAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://api.coindesk.com"
private val BASE_URL2 = "https://api.coinpaprika.com"
private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()
private val retrofit2 = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL2).build()

object API {
    val cryptosAPI: CryptosAPI by lazy {
        retrofit.create(CryptosAPI::class.java)
    }
}

object API2 {
    val cryptosAPI: CryptosAPI by lazy {
        retrofit2.create(CryptosAPI::class.java)
    }
}