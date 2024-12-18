package com.aplikasi.musicplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.musicplayer.viewmodel.SongViewModel

class FavouriteFragment : Fragment() {
    private lateinit var songViewModel: SongViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = SongAdapter(
            mutableListOf(), // List lagu yang kosong
            fragmentType = SongAdapter.TYPE_PENDENGAR, // Menentukan jenis tampilan
            onAddToFavoriteClick = { song ->
                // Implementasi klik lagu favorit
            }
        )


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize SongViewModel
        songViewModel = ViewModelProvider(this).get(SongViewModel::class.java)

        // Observe changes to the songs list
        songViewModel.songs.observe(viewLifecycleOwner) { songs ->
            adapter.updateData(songs) // Update data di adapter dengan daftar lagu terbaru
        }

        // Fetch the songs from the ViewModel
        songViewModel.fetchSongs()

        return view
    }
}