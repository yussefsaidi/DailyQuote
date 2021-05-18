package com.yussefsaidi.dailyquote.features.quotes

import android.content.Context
import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.yussefsaidi.dailyquote.core.NotificationCenter
import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteWorker @WorkerInject constructor(
    private val quoteRepository: QuoteRepository,
    private val notificationCenter: NotificationCenter,
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("DEBUG", "doWork: get new quote")
        withContext(Dispatchers.IO) {
            val quote = quoteRepository.getRandomQuote()
            notificationCenter.showQuoteNotification(quote)
        }
        return Result.success()
    }

}