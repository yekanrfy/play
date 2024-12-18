package com.aplikasi.musicplayer.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_table")
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val artist: String,
    val duration: String
)
