package br.com.mcontigo.teste.isearch.presentation.songdetail

import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import br.com.mcontigo.teste.isearch.R
import br.com.mcontigo.teste.isearch.domain.track.Track
import com.bumptech.glide.Glide


class SongDetailActivity : AppCompatActivity(), SongDetailContract.View {
    private lateinit var context: Context
    private lateinit var main: LinearLayout
    private lateinit var imgArtwork: ImageView
    private lateinit var txtArtistName: TextView
    private lateinit var txtGenre: TextView
    private lateinit var txtPrice: TextView
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@SongDetailActivity

        main = findViewById<View>(R.id.song_detail_main) as LinearLayout
        imgArtwork = findViewById<View>(R.id.imgArtworkDetail) as ImageView
        txtArtistName = findViewById<View>(R.id.artist_name_detail) as TextView
        txtGenre = findViewById<View>(R.id.genre_detail) as TextView
        txtPrice = findViewById<View>(R.id.price_detail) as TextView
        videoView = findViewById<View>(R.id.videoView) as VideoView

        try {
            displayTrack(intent.getSerializableExtra("track") as Track)
        } catch (e: Exception) {
            displayMessage("Problem while getting song info, Try again.")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun displayTrack(track: Track) {

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = track.trackName
        }

        val artworkUrl = track.artworkUrl100
        Glide.with(context).load(artworkUrl).into(imgArtwork)

        txtArtistName.text = track.artistName
        txtGenre.text = track.primaryGenreName
        txtPrice.text = String.format("US $ %s", track.trackPrice.toString())

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val video = Uri.parse(track.previewUrl)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(video)
        videoView.start()
    }
}
