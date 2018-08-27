package br.com.mcontigo.teste.isearch.data.remote

import br.com.mcontigo.teste.isearch.domain.track.model.TrackModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {

    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getTracks(@Query("term") term: String): Call<TrackModel>
}