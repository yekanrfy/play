package com.aplikasi.musicplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.musicplayer.Model.Song
import com.aplikasi.musicplayer.databinding.ItemSongPendengarBinding // Untuk Pendengar
import com.aplikasi.musicplayer.databinding.ItemSongPenyanyiBinding // Untuk Penyanyi

class SongAdapter(
    private val songs: MutableList<Song>,
    private val onDeleteClick: (Song) -> Unit = {},
    private val onEditClick: (Song) -> Unit = {},
    private val onAddToFavoriteClick: (Song) -> Unit = {}, // Tambahkan callback untuk add to favorite
    private val fragmentType: Int // Menentukan fragment mana yang memanggil adapter
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_PENDENGAR = 0
        const val TYPE_PENYANYI = 1
    }

    // ViewHolder untuk Pendengar (item_song_pendengar)
    inner class SongViewHolderPendengar(private val binding: ItemSongPendengarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.tvSongTitle.text = song.title
            binding.tvSongArtist.text = song.artist
            binding.tvSongDuration.text = song.duration
            binding.btnAddToFavorite.setOnClickListener {
                onAddToFavoriteClick(song) // Menambahkan lagu ke favorit
            }
        }
    }

    // ViewHolder untuk Penyanyi (item_song_penyanyi)
    inner class SongViewHolderPenyanyi(private val binding: ItemSongPenyanyiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.tvSongTitle.text = song.title
            binding.tvSongArtist.text = song.artist
            binding.tvSongDuration.text = song.duration
            binding.btnEdit.setOnClickListener {
                onEditClick(song)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(song)
            }
        }
    }

    // Menentukan ViewHolder yang digunakan berdasarkan fragment
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PENDENGAR -> {
                val binding = ItemSongPendengarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SongViewHolderPendengar(binding)
            }
            TYPE_PENYANYI -> {
                val binding = ItemSongPenyanyiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SongViewHolderPenyanyi(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // Mengikat data dengan ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val song = songs[position]
        when (holder) {
            is SongViewHolderPendengar -> holder.bind(song)
            is SongViewHolderPenyanyi -> holder.bind(song)
        }
    }

    override fun getItemCount(): Int = songs.size

    // Menentukan jenis tampilan berdasarkan fragment yang digunakan
    override fun getItemViewType(position: Int): Int {
        return fragmentType // Menggunakan fragmentType untuk memilih tampilan yang sesuai
    }

    // Fungsi untuk memperbarui data di adapter
    fun updateData(newSongs: List<Song>) {
        songs.clear()
        songs.addAll(newSongs)
        notifyDataSetChanged()
    }
}
