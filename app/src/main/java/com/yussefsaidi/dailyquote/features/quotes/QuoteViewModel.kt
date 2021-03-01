package com.yussefsaidi.dailyquote.features.quotes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepository
import com.yussefsaidi.dailyquote.core.network.quotes.RandomQuoteResponse
import kotlinx.coroutines.launch

class QuoteViewModel @ViewModelInject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    val randomQuoteLiveData = MutableLiveData<RandomQuoteResponse>()

    fun getRandomQuote() {
        viewModelScope.launch {
            randomQuoteLiveData.postValue(quoteRepository.getRandomQuote())
        }

    }
}