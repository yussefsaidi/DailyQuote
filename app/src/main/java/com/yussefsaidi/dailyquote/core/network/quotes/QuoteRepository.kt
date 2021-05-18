package com.yussefsaidi.dailyquote.core.network.quotes

interface QuoteRepository {
    suspend fun getRandomQuote(): RandomQuoteResponse
}