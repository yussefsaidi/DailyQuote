package com.yussefsaidi.dailyquote.core.network.quotes

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interface to define endpoints for Retrofit related to quotes.
 */

interface QuoteService {

    companion object {
        const val DEFAULT_QUOTE_URL = "https://api.forismatic.com/api/1.0/"
    }

    @Headers("Accept: application/json")
    @GET(".")
    fun getRandomQuote(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("lang") language: String
    ): Call<RandomQuoteResponse>

}