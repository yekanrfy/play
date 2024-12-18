package com.aplikasi.musicplayer.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.Query
import com.aplikasi.musicplayer.Model.Song

@Dao
interface MusicDao {

    @Insert
    suspend fun insert(song: Song)

    @Update
    suspend fun update(song: Song)

    @Delete
    suspend fun delete(song: Song)

    // Method untuk mendapatkan semua lagu
    @Query("SELECT * FROM song_table")
    fun getAllSongs(): LiveData<List<Song>>

    // Method untuk mendapatkan lagu berdasarkan id
    @Query("SELECT * FROM song_table WHERE id = :id LIMIT 1")
    fun getSongById(id: Int): Song?
}
