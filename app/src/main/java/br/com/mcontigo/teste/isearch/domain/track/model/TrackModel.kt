package br.com.mcontigo.teste.isearch.domain.track.model

import br.com.mcontigo.teste.isearch.domain.track.Track
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class TrackModel {

    @SerializedName("resultCount")
    @Expose
    private var resultCount: Int? = null
    @SerializedName("results")
    @Expose
    private var tracks: List<Track>? = null

    fun getResultCount(): Int? {
        return resultCount
    }

    fun setResultCount(resultCount: Int?) {
        this.resultCount = resultCount
    }

    fun getTracks(): List<Track>? {
        return tracks
    }

    fun setTracks(tracks: List<Track>) {
        this.tracks = tracks
    }
}