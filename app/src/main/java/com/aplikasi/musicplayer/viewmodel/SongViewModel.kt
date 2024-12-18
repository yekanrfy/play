package com.aplikasi.musicplayer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aplikasi.musicplayer.Database.MusicDao
import com.aplikasi.musicplayer.Database.MusicDatabase
import com.aplikasi.musicplayer.Model.Song
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {

    private val musicDao: MusicDao = MusicDatabase.getDatabase(application).musicDao()
    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> get() = _songs

    // Fungsi untuk mengambil data lagu
    fun fetchSongs() {
        viewModelScope.launch {
            // Ambil data lagu dari database dan update _songs
            _songs.value = musicDao.getAllSongs().value
        }
    }


    // Fungsi untuk menambah lagu
    fun addSong(song: Song) {
        viewModelScope.launch {
            musicDao.insert(song)
            fetchSongs()  // Mengambil kembali data setelah penambahan
        }
    }

    // Fungsi untuk mengupdate lagu
    fun updateSong(id: Int, song: Song) {
        viewModelScope.launch {
            musicDao.update(song)
            fetchSongs()  // Mengambil kembali data setelah pembaruan
        }
    }

    // Fungsi untuk menghapus lagu
    fun deleteSong(id: Int) {
        viewModelScope.launch {
            val song = musicDao.getSongById(id) // Pastikan kamu sudah memiliki metode untuk mencari lagu berdasarkan ID
            song?.let {
                musicDao.delete(it)
                fetchSongs()  // Mengambil kembali data setelah penghapusan
            }
        }
    }
}
