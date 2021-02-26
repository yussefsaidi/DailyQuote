package com.yussefsaidi.dailyquote.features.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yussefsaidi.dailyquote.R
import com.yussefsaidi.dailyquote.core.network.quotes.RandomQuoteResponse

class RandomQuoteAdapter : RecyclerView.Adapter<RandomQuoteAdapter.RandomQuoteViewHolder>() {

    private var randomQuotes = mutableListOf<RandomQuoteResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomQuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return RandomQuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RandomQuoteViewHolder, position: Int) {
        holder.bind(randomQuotes[position])
    }

    override fun getItemCount() = randomQuotes.size

    fun addQuote(newRandomQuote: RandomQuoteResponse) {
        randomQuotes.add(newRandomQuote)
        notifyDataSetChanged()
    }

    class RandomQuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(quote: RandomQuoteResponse) {
            itemView.findViewById<TextView>(R.id.quoteTextView).text = quote.quoteText
            itemView.findViewById<TextView>(R.id.quoteAuthorTextView).text = "-" + quote.quoteAuthor
        }
    }
}