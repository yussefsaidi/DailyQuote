package com.yussefsaidi.dailyquote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.yussefsaidi.dailyquote.core.NotificationCenter
import com.yussefsaidi.dailyquote.features.quotes.QuoteViewModel
import com.yussefsaidi.dailyquote.features.quotes.RandomQuoteFragment.Companion.QUOTE_BR
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random_quote.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var notificationCenter: NotificationCenter
    private val quoteViewModel by viewModels<QuoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(quoteReceiver, IntentFilter(QUOTE_BR))

        quoteViewModel.randomQuoteLiveData.observe(this, Observer {
            Log.d("TEST", "notification quote: " + quoteViewModel.randomQuoteLiveData.value)
            notificationCenter.showQuoteNotification(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.homeNavigationFragment)
    }

    private val quoteReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("TEST", "Received alarm intent")
            quoteViewModel.getRandomQuote()
        }
    }
}