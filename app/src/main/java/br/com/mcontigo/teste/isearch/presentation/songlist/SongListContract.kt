package br.com.mcontigo.teste.isearch.presentation.songlist

import br.com.mcontigo.teste.isearch.domain.track.Track


internal class SongListContract {

    internal interface View {
        fun displayMessage(message: String)

        fun setLoadingIndicator(isLoading: Boolean)

        fun displayTracks(dataTracks: List<Track>)
    }

    internal interface Presenter {
        fun getTracks(term: String)
    }
}
