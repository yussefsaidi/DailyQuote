package com.yussefsaidi.dailyquote.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.yussefsaidi.dailyquote.core.NotificationCenter.Companion.QUOTE_NOTIFICATION_CHANNEL_ID
import com.yussefsaidi.dailyquote.core.NotificationCenter.Companion.QUOTE_NOTIFICATION_CHANNEL_NAME
import dagger.hilt.android.HiltAndroidApp
import java.util.ArrayList
import javax.inject.Inject

@HiltAndroidApp
class DailyQuoteApplication : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        val notificationManager: NotificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = ArrayList<NotificationChannel>()

            val channelNotification = NotificationChannel(
                QUOTE_NOTIFICATION_CHANNEL_ID,
                QUOTE_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channels.add(channelNotification)
            notificationManager.createNotificationChannels(channels)
        }
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

}