package wikibook.learnandriod.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*


class QuoteMainActivity : AppCompatActivity() {
    private lateinit var quotes: MutableList<Quote>
    private lateinit var pref: SharedPreferences

//    fun to store first quote
    fun inititalizeQuotes() {
        val initialized = pref.getBoolean("initialized", false)
        if (!initialized) {
            Quote.saveToPreference(pref, 0, "괴로운 시련처럼 보이는 것이 뜻밖의 좋은 일일 때가 많다.", "오스카 와일드")
            Quote.saveToPreference(pref, 1, "성공한 사람이 되려고 노력하기보다 가치있는 사람이 되려고 노력하라.", "알버트 아인슈타인")
            Quote.saveToPreference(pref, 2, "추구할 수 있는 용기가 있다면 우리의 모든 꿈은 이루어질 수 있다.", "월트 디즈니")
            Quote.saveToPreference(pref, 3, "실패에서부터 성공을 만들어 내라. 좌절과 실패는 성공으로 가는 가장 확실한 디딤돌이다", "데일 카네기")
            Quote.saveToPreference(pref, 4, "창조적인 삶을 살려면 내가 틀릴지도 모른다는 공포를 버려야 한다.")

            val editor = pref.edit()
            editor.putBoolean("initialized", true)
            editor.apply()
        }

    }

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