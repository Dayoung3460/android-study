package wikibook.learnandriod.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class QuoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_list_activity)

        val currentQuoteSize = intent.getIntExtra("quote_size", 0)
        Toast.makeText(this, "You have ${currentQuoteSize} quotes stored.", Toast.LENGTH_SHORT).show()
    }
}