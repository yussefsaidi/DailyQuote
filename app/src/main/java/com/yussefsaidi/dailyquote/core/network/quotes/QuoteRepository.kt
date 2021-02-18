package com.yussefsaidi.dailyquote.core.network.quotes

import androidx.lifecycle.LiveData

interface QuoteRepository {
    suspend fun getRandomQuote(): RandomQuoteResponse?
}