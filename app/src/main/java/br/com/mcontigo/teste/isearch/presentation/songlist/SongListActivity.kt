package br.com.mcontigo.teste.isearch.presentation.songlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.SearchManager
import android.support.v4.view.MenuItemCompat
import android.content.Context
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import br.com.mcontigo.teste.isearch.R
import br.com.mcontigo.teste.isearch.domain.track.Track
import br.com.mcontigo.teste.isearch.presentation.songlist.adapter.SongAdapter
import com.cooltechworks.views.shimmer.ShimmerRecyclerView


class SongListActivity : AppCompatActivity(), SongListContract.View {

    override fun displayTracks(dataTracks: List<Track>) {
    }

    private lateinit var context: Context
    private lateinit var main: LinearLayout
    private lateinit var txtNoSongs: TextView
    private lateinit var listTracks: ShimmerRecyclerView

    private var adapter: SongAdapter? = null

    private var presenter: SongListPresenter = SongListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        context = this@SongListActivity

        main = findViewById<View>(R.id.song_list_main) as LinearLayout
        txtNoSongs = findViewById<View>(R.id.txtNoSongs) as TextView
        listTracks = findViewById<View>(R.id.listSongs) as ShimmerRecyclerView

        listTracks.showShimmerAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Search for Songs, Artists & More"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    fun search(strTerm: String) {
        txtNoSongs.visibility = View.GONE
        listTracks.visibility = View.VISIBLE
        adapter!!.notifyDataSetChanged()

        setLoadingIndicator(true)
        listTracks.postDelayed({ presenter.getTracks(strTerm) }, 2000)
    }

    override fun displayMessage(message: String) {
        setLoadingIndicator(false)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun setLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            listTracks.showShimmerAdapter()
        } else {
            listTracks.hideShimmerAdapter()
        }
    }

}

