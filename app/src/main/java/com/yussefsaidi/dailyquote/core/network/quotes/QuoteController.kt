package com.yussefsaidi.dailyquote.core.network.quotes

import android.os.Bundle
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class QuoteController @Inject constructor() {

    private val quoteService = createApiService()

    /**
     *
     */
    fun getRandomQuote(quoteListener: OnQuoteListener) {
        quoteService.getRandomQuote("getQuote", "json", "en")
            .enqueue(object : Callback<RandomQuoteResponse> {
                override fun onResponse(
                    call: Call<RandomQuoteResponse>,
                    response: Response<RandomQuoteResponse>
                ) {
                    when (response.code()) {
                        200 -> {
                            if (response.body() == null) {
                                Log.e("TAG", "No response data")
                            } else {
                                quoteListener.onQuoteSuccess(
                                    response.body()!!
                                )
                            }
                        }
                        else -> {
                            quoteListener.onQuoteFailure(response.message(), response.code())
                        }
                    }
                }

                override fun onFailure(call: Call<RandomQuoteResponse>, t: Throwable) {
                    quoteListener.onQuoteFailure(t.message, HttpsURLConnection.HTTP_CONFLICT)
                }

            })
    }

    companion object {
        /**
         * Creates a Retrofit API service based on the [ServiceType] provided.
         *
         * @param serviceType The type of service to build.
         * @return A configured Retrofit instance of type [Any].
         */
        fun createApiService() =
            buildRetrofitInstance(QuoteService.DEFAULT_QUOTE_URL).create(
                QuoteService::class.java
            )

        /**
         * Builds a retrofit instance with the provided url and a GSON converter.
         *
         * @param baseUrl The base url to be used for requests.
         * @return A configured Retrofit instance with a base url and a GSON converter.
         */
        private fun buildRetrofitInstance(baseUrl: String, supportProtobuff: Boolean = false) =
            Retrofit.Builder()
                .baseUrl(baseUrl).apply {
                    addConverterFactory(GsonConverterFactory.create())
                }
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(getRetrofitHttpClient())
                .build()


        /**
         * Builds an [OkHttpClient] with an [HttpLoggingInterceptor] if the app is in debug mode.
         *
         * @return an [OkHttpClient] to be used by a Retrofit service.
         */
        private fun getRetrofitHttpClient() =
            OkHttpClient.Builder().build()
    }

    /**
     * Interface to be called back if the quote retrieval succeeds/fail.
     */
    interface OnQuoteListener {
        fun onQuoteSuccess(quoteResponse: RandomQuoteResponse)
        fun onQuoteFailure(message: String?, statusCode: Int)
    }
}