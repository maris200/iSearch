package br.com.mcontigo.teste.isearch.data

import br.com.mcontigo.teste.isearch.data.remote.APIService
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object ServiceFactory {

    val instance: APIService
        get() {

            val baseUrl = "https://itunes.apple.com/"

            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(APIService::class.java)
        }
}