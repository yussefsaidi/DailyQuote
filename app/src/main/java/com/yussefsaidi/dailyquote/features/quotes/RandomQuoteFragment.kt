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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random_quote.*

@AndroidEntryPoint
class RandomQuoteFragment: Fragment() {

    private val navController: NavController by lazy {
        Navigation.findNavController(
            activity as FragmentActivity,
            R.id.homeNavigationFragment
        )
    }

    private val randomQuoteAdapter = RandomQuoteAdapter()
    private val quoteViewModel by viewModels<QuoteViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_random_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initRecyclerView()
        quoteViewModel.getRandomQuote()
        nextQuoteButton.setOnClickListener {
            quoteViewModel.getRandomQuote()
        }


        quoteViewModel.randomQuoteLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("TEST", "new quote: $it")
            /*it?.let {
                randomQuoteAdapter.addQuote(it)
            }*/
            quoteTextView.text = it.quoteText
            quoteAuthorTextView.text = it.quoteAuthor


        })
    }

    /*fun initRecyclerView(){
        quotesRecyclerView.setHasFixedSize(true)
        quotesRecyclerView.layoutManager = LinearLayoutManager(activity)
        quotesRecyclerView.adapter = randomQuoteAdapter
    }*/
}