package com.yussefsaidi.dailyquote.core.network.quotes

import android.util.Log
import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class QuoteRepositoryImpl
@Inject constructor(private val quoteController: QuoteController) : QuoteRepository {
    override suspend fun getRandomQuote(): RandomQuoteResponse? {
        return suspendCoroutine {
            quoteController.getRandomQuote(object : QuoteController.OnQuoteListener {
                override fun onQuoteSuccess(quoteResponse: RandomQuoteResponse) {
                    Log.d("TEST", "onQuoteSuccess: ")
                    it.resume(quoteResponse)
                }

                override fun onQuoteFailure(message: String?, statusCode: Int) {
                    Log.d("TEST", "onQuoteFailure: " + statusCode + "error: " + message)
                    it.resume(null)
                }
            })
        }
    }
}