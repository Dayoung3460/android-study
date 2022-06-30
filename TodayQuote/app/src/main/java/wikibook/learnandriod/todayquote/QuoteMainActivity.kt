package wikibook.learnandriod.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*


class QuoteMainActivity : AppCompatActivity() {
    private lateinit var quotes: MutableList<Quote>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_activity_main)

        val quoteText = findViewById<TextView>(R.id.quote_text)
        val quoteFrom = findViewById<TextView>(R.id.quote_from)

        quotes = mutableListOf()
        quotes.add(Quote(1, "명언1", "출처1"))
        quotes.add(Quote(2, "명언2", "출처2"))
        quotes.add(Quote(3, "명언3", "출처3"))

        val randomIndex = Random().nextInt(quotes.size)
        val randomQuote = quotes[randomIndex]

        quoteText.text = randomQuote.text
        quoteFrom.text = randomQuote.from
    }
}