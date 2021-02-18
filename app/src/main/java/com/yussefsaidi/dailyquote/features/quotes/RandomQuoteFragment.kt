package com.yussefsaidi.dailyquote.features.quotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yussefsaidi.dailyquote.R
import com.yussefsaidi.dailyquote.core.network.quotes.RandomQuoteResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random_quote.*

@AndroidEntryPoint
class RandomQuoteFragment: Fragment(){

    private val navController: NavController by lazy {
        Navigation.findNavController(
            activity as FragmentActivity,
            R.id.homeNavigationFragment
        )
    }

    private val quoteViewModel by viewModels<QuoteViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_random_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteViewModel.getRandomQuote()

        quoteViewModel.randomQuote.observe(viewLifecycleOwner, Observer {
            Log.d("TEST", "new quote: " + it)
            it?.let {
                quoteTextView.text = it.quoteText
            }
        })

        quoteButton.setOnClickListener {
            Log.d("TEST", "onViewCreated: ")
            quoteViewModel.getRandomQuote()
        }
    }
}