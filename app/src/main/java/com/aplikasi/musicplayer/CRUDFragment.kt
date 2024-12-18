package com.aplikasi.musicplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aplikasi.musicplayer.Model.Song
import com.aplikasi.musicplayer.databinding.FragmentCrudBinding
import com.aplikasi.musicplayer.viewmodel.SongViewModel

class CRUDFragment : Fragment() {

    private lateinit var songViewModel: SongViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCrudBinding.inflate(inflater, container, false)

        songViewModel = ViewModelProvider(this).get(SongViewModel::class.java)

        // Observasi data lagu
        songViewModel.songs.observe(viewLifecycleOwner, Observer { songs ->
            // Mengatur adapter RecyclerView dengan onDeleteClick dan onEditClick
            binding.recyclerView.adapter = SongAdapter(
                songs.toMutableList(),
                fragmentType = SongAdapter.TYPE_PENYANYI, // Pilih fragment type yang sesuai
                onDeleteClick = { song ->
                    // Aksi saat tombol hapus diklik
                    songViewModel.deleteSong(song.id)
                },
                onEditClick = { song ->
                    // Aksi saat tombol edit diklik
                    songViewModel.updateSong(song.id, song)
                }
            )
        })

        // Ambil data lagu dari API
        songViewModel.fetchSongs()

        return binding.root
    }

    // Fungsi untuk menambah lagu
    private fun addSong() {
        val song = Song(title = "New Song", artist = "Artist", duration = "3:30")
        songViewModel.addSong(song)
    }

    // Fungsi untuk mengupdate lagu
    private fun updateSong() {
        val song = Song(id = 1, title = "Updated Song", artist = "Updated Artist", duration = "4:00")
        songViewModel.updateSong(1, song)
    }

    // Fungsi untuk menghapus lagu
    private fun deleteSong() {
        songViewModel.deleteSong(1)
    }
}
