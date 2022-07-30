package com.example.cryptos.network.Remote

import com.example.cryptos.data.model.AllCryptosItem
import com.example.cryptos.data.model.Bitcoin
import retrofit2.Call
import retrofit2.http.GET

interface CryptosAPI {
    @GET("/v1/bpi/currentprice.json")
    fun getBitcoinData(): Call<Bitcoin>


    @GET("/v1/coins")
    fun getAllCryptosData(): Call<List<AllCryptosItem>>
}