package com.yussefsaidi.dailyquote.core.network.quotes

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import javax.net.ssl.HttpsURLConnection

class QuoteTest {

    private lateinit var quoteController: QuoteController
    private lateinit var countDownLatch: CountDownLatch

    @Before
    fun setUp(){
        quoteController = QuoteController()
    }

    @Test
    fun testGetRandomQuote() {
        val responseCode = getRandomQuote()
        println("Response inside test: $responseCode")
        Assert.assertTrue(responseCode in 200..300)
    }

    private fun getRandomQuote(): Int {
        countDownLatch = CountDownLatch(1)
        var requestStatusCode = HttpsURLConnection.HTTP_OK

        quoteController.getRandomQuote(object : QuoteController.OnQuoteListener {
            override fun onQuoteSuccess(quoteResponse: RandomQuoteResponse) {
                println("Quote returned: ${quoteResponse.quoteText}")
                countDownLatch.countDown()
            }

            override fun onQuoteFailure(message: String?, statusCode: Int) {
                println("Failed: $message, with code: $statusCode")
                countDownLatch.countDown()
            }

        })
        countDownLatch.await()
        return requestStatusCode
    }
}