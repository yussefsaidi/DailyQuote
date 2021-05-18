package com.yussefsaidi.dailyquote.features.quotes.di

import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepository
import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 abstract class QuoteModule {

    @Singleton
    @Binds
    abstract fun bindQuoteRepository(impl: QuoteRepositoryImpl): QuoteRepository

    /*@Singleton
    @Binds
    abstract fun providesNotificationCenter(notificationCenter: NotificationCenter) : NotificationCenter*/

    /*@Singleton
    @Provides
    fun providesWorkManager(@ApplicationContext appContext: Context): WorkManager =
        WorkManager.getInstance(appContext)*/

}
