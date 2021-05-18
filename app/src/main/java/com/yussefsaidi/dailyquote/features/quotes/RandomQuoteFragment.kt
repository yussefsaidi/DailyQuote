package com.yussefsaidi.dailyquote.features.quotes

import android.content.Intent
import android.content.Intent.*
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
import com.yussefsaidi.dailyquote.core.NotificationCenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random_quote.*
import javax.inject.Inject

@AndroidEntryPoint
class RandomQuoteFragment : Fragment() {

    @Inject
    lateinit var notificationCenter: NotificationCenter

    companion object {
        const val QUOTE_BR = "com.yussefsaidi.quote_br"
    }

    private val navController: NavController by lazy {
        Navigation.findNavController(
            activity as FragmentActivity,
            R.id.homeNavigationFragment
        )
    }

    private val quoteViewModel by viewModels<QuoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteViewModel.getRandomQuote()
        nextQuoteButton.setOnClickListener {
            quoteViewModel.getRandomQuote()
        }

        shareQuoteButton.setOnClickListener {
            val sharingIntent = Intent(ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(EXTRA_SUBJECT, "Check out this cool quote")
            sharingIntent.putExtra(
                EXTRA_TEXT,
                "" + quoteTextView.text + "\n" + quoteAuthorTextView.text
            )
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

        quoteViewModel.randomQuoteLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("TEST", "new quote: $it")
            quoteTextView.text = it.quoteText
            quoteAuthorTextView.text = "-" + it.quoteAuthor
            if (it.quoteAuthor.isNotBlank()) quoteAuthorTextView.visibility = View.VISIBLE
            else quoteAuthorTextView.visibility = View.GONE
        })

        quoteViewModel.setupQuoteNotifications()
    }
}