package com.yussefsaidi.dailyquote.core.network.quotes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Response model for random quote endpoint
 *
 * Created on 02/06/2021
 *
 * @author Yussef
 */

@Parcelize
data class RandomQuoteResponse(
        val quoteText: String,
        val quoteAuthor: String,
        val senderName: String,
        val senderLink: String,
        val quoteLink: String
) : Parcelable
