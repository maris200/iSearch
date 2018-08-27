package br.com.mcontigo.teste.isearch.presentation.songlist.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.teste.isearch.presentation.songdetail.SongDetailActivity
import android.content.Intent
import android.widget.TextView
import android.widget.LinearLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import br.com.teste.isearch.R
import br.com.teste.isearch.domain.track.Track


class SongAdapter internal constructor(internal var context: Context, private val trackList: List<Track>) : RecyclerView.Adapter<SongAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var row: LinearLayout = view.findViewById(R.id.song_item_row)
        var imgTrackArtwork: ImageView = view.findViewById(R.id.artwork) as ImageView
        var txtTrackName: TextView = view.findViewById(R.id.track_name)
        var txtArtistNameNGenre: TextView = view.findViewById(R.id.artist_name_and_genre)
        var txtPrice: TextView = view.findViewById(R.id.price)

        init {
            row.setOnClickListener {

                //
                val intent = Intent(context, SongDetailActivity::class.java)
                intent.getStringExtra("track")
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val track = trackList[position]

        val artworkUrl = track.artworkUrl100
        Glide.with(context).load(artworkUrl).into(holder.imgTrackArtwork)

        holder.txtTrackName.setText(track.trackName)
        holder.txtArtistNameNGenre.text = track.artistName + " | " + track.primaryGenreName
        holder.txtPrice.text = String.format("US $ %s", track.trackPrice.toString())
    }

    override fun getItemCount(): Int {
        return trackList.size
    }
}


