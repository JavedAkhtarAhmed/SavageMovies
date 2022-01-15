package com.javed.savagemovies.api

import com.javed.savagemovies.modelclasses.MoviesResponses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserServices {
    @GET("shows?q=all")
    suspend fun movie(): Response<MutableList<MoviesResponses>>


    @GET("shows")
    suspend fun movieSearch(@Query("q") search_term: String): Response<MutableList<MoviesResponses>>
//
//    @GET("/quotes")
//    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>


}