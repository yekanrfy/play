package com.aplikasi.musicplayer.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplikasi.musicplayer.Model.Song

@Database(entities = [Song::class], version = 1, exportSchema = false)
abstract class MusicRoomDatabase: RoomDatabase() {
    abstract fun musicDao(): MusicDao

    companion object {
        @Volatile
        private var INSTANCE: MusicRoomDatabase? = null

        fun getDatabase(context: Context): MusicRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicRoomDatabase::class.java,
                    "music_database"
                ).fallbackToDestructiveMigration()  // Menangani perubahan versi database
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
