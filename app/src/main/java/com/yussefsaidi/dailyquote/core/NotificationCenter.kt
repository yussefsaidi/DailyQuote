package com.yussefsaidi.dailyquote.core

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleObserver
import com.yussefsaidi.dailyquote.R
import com.yussefsaidi.dailyquote.core.network.quotes.RandomQuoteResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationCenter @Inject constructor(@ApplicationContext private val activity: Context) :
    LifecycleObserver {

    private val notificationCenterScope = CoroutineScope(Dispatchers.IO)

    companion object {
        const val QUOTE_NOTIFICATION_CHANNEL_ID = "QuoteNotification"
        const val QUOTE_NOTIFICATION_CHANNEL_NAME = "Quote Notification"
    }

    fun showQuoteNotification(quoteResponse: RandomQuoteResponse) = notificationCenterScope.launch {
        showPushNotification(
            quoteResponse.quoteText.hashCode(),
            createQuoteNotificationBuilder(quoteResponse)
        )
    }

    private fun showPushNotification(id: Int, notification: Notification) {
        (activity.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(id, notification)
    }

    private fun createQuoteNotificationBuilder(quoteResponse: RandomQuoteResponse): Notification {
        val notificationBuilder =
            NotificationCompat.Builder(activity, QUOTE_NOTIFICATION_CHANNEL_ID)

        notificationBuilder.setSmallIcon(R.drawable.ic_dailyquote_foreground)
            .setContentTitle("Quote")
            .setContentText(quoteResponse.quoteText)
            .setAutoCancel(true)

        return notificationBuilder.build()
    }
}