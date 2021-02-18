package com.yussefsaidi.dailyquote.core.network.quotes

/**
 * Response model for random quote endpoint
 *
 * Created on 02/06/2021
 *
 * @author Yussef
 */
data class RandomQuoteResponse(
        val quoteText: String,
        val quoteAuthor: String,
        val senderName: String,
        val senderLink: String,
        val quoteLink: String
)
