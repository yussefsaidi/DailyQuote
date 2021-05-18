package com.yussefsaidi.dailyquote.features.quotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepository
import com.yussefsaidi.dailyquote.core.network.quotes.RandomQuoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val workManager: WorkManager
) : ViewModel() {

    val randomQuoteLiveData = MutableLiveData<RandomQuoteResponse>()

    fun getRandomQuote() {
        viewModelScope.launch {
            randomQuoteLiveData.postValue(quoteRepository.getRandomQuote())
        }
    }

    fun setupQuoteNotifications() {
        viewModelScope.launch {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val periodicWork = PeriodicWorkRequestBuilder<QuoteWorker>(1, TimeUnit.MINUTES)
                .setConstraints(constraints).build()

            workManager.enqueue(periodicWork)
        }
    }
}