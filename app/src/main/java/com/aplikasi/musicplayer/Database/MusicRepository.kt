package com.aplikasi.musicplayer.Database

import android.app.Application
import com.aplikasi.musicplayer.Model.Song

class MusicRepository(application: Application) {

    private val musicDao: MusicDao = MusicDatabase.getDatabase(application).musicDao()

    // Fungsi untuk memasukkan lagu
    suspend fun insert(song: Song): Long {
        return musicDao.insert(song)
    }
}
