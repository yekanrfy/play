package com.aplikasi.musicplayer.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://ppbo-api.vercel.app/hL3IP/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Tambahkan deklarasi apiService di sini
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
