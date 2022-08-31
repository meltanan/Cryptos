package com.example.cryptos.network.Remote

import com.example.cryptos.data.model.AllCryptosItem
import com.example.cryptos.data.model.Bitcoin
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CryptosAPI {
    @GET("/v1/bpi/currentprice.json")
    fun getBitcoinData(): Call<Bitcoin>

    @GET
    fun getAllCryptosData(@Url url: String): Call<List<AllCryptosItem>>
}