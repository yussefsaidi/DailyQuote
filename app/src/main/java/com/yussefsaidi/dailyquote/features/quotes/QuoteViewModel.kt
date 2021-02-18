package com.yussefsaidi.dailyquote.features.quotes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yussefsaidi.dailyquote.core.network.quotes.QuoteRepository
import com.yussefsaidi.dailyquote.core.network.quotes.RandomQuoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuoteViewModel @ViewModelInject constructor(
     private val quoteRepository: QuoteRepository
) : ViewModel() {

    val randomQuote = MutableLiveData<RandomQuoteResponse>()

    fun getRandomQuote(){
        viewModelScope.launch {
            randomQuote.postValue(quoteRepository.getRandomQuote())
        }
    }
}