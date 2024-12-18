package com.aplikasi.musicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.aplikasi.musicplayer.Model.Song
import com.aplikasi.musicplayer.viewmodel.SongViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var musicViewModel: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi SongViewModel
        musicViewModel = ViewModelProvider(this).get(SongViewModel::class.java)

        // Temukan BottomNavigationView dan NavController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)

        // Sinkronisasi BottomNavigationView dengan NavController
        bottomNavigationView.setupWithNavController(navController)

        // Mengecek status login pengguna
        if (SharedPrefManager.isUserLoggedIn(this)) {
            val username = SharedPrefManager.getUsername(this)
            if (username == "listener") {
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            } else if (username == "singer") {
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            }
        } else {
            navController.navigate(R.id.loginFragment)
        }

        // Misalnya, kamu ingin menambah lagu (sebagai contoh penggunaan fungsi suspend)
        val song = Song(title = "New Song", artist = "New Artist", album = "New Album")

        // Menjalankan fungsi insert song dalam coroutine
        lifecycleScope.launch {
            // Pastikan kamu sudah memanggil ViewModel terlebih dahulu
            musicViewModel.insertSong(song)
        }
    }
}
