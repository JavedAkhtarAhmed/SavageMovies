package com.javed.savagemovies.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val Base_URL = "https://api.tvmaze.com/search/"

    fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}