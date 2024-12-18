package com.aplikasi.musicplayer.Network

import com.aplikasi.musicplayer.Model.Song
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    // Menggunakan Call untuk request async
    @GET("songs")  // Pastikan endpoint API ini benar
    fun getSongs(): Call<List<Song>>

    // Menggunakan suspend function untuk coroutine
    @GET("songs")
    suspend fun getSongsSuspend(): List<Song>

    // Tambah lagu menggunakan POST
    @POST("songs")
    suspend fun addSong(@Body song: Song)

    // Update lagu menggunakan PUT
    @PUT("songs/{id}")
    suspend fun updateSong(@Path("id") id: Int, @Body song: Song)

    // Hapus lagu menggunakan DELETE
    @DELETE("songs/{id}")
    suspend fun deleteSong(@Path("id") id: Int)
}
