package com.yussefsaidi.dailyquote.features.quotes.di

import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepository
import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class QuoteModule {
    @Binds
    abstract fun providesQuoteRepository(impl: QuoteRepositoryImpl): QuoteRepository
}
