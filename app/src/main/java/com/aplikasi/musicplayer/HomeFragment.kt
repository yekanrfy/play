package com.aplikasi.musicplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.musicplayer.Model.Song
import com.aplikasi.musicplayer.viewmodel.SongViewModel

class HomeFragment : Fragment() {
    // Ubah tipe songViewModel dari Song ke SongViewModel
    private lateinit var songViewModel: SongViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Pastikan songViewModel adalah SongViewModel
        songViewModel = ViewModelProvider(this).get(SongViewModel::class.java)

        // Observe LiveData songs dari ViewModel
        songViewModel.songs.observe(viewLifecycleOwner) { songs ->
            // Mengonversi List<Song> menjadi MutableList<Song>
            recyclerView.adapter = SongAdapter(songs.toMutableList(), fragmentType = SongAdapter.TYPE_PENDENGAR)
        }


        // Fetch songs dari ViewModel
        songViewModel.fetchSongs()
        return view
    }
}
