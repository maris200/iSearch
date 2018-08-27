package br.com.mcontigo.teste.isearch.presentation.songlist

import br.com.teste.isearch.domain.track.model.TrackModel
import br.com.teste.isearch.data.ServiceFactory
import br.com.teste.isearch.data.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class SongListPresenter(private val songListView: SongListContract.View) : SongListContract.Presenter {

    override fun getTracks(term: String) {
        val service = ServiceFactory.instance
        val trackModelCall = service.getTracks(term)
        trackModelCall.enqueue(object : Callback<TrackModel> {
            override fun onResponse(call: Call<TrackModel>, response: Response<TrackModel>) {
                val trackModel = response.body()
                if (trackModel.getResultCount()!! > 0) {
                    songListView.displayTracks(trackModel.getTracks()!!)
                } else {
                    songListView.displayMessage("No songs found, Try again.")
                }
            }

            override fun onFailure(call: Call<TrackModel>, t: Throwable) {
                songListView.displayMessage("No songs found, Try again.")
            }
        })
    }
}
