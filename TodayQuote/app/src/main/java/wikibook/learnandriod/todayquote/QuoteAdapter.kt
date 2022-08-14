package wikibook.learnandriod.todayquote

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(private val dataList: List<Quote>): RecyclerView.Adapter<QuoteAdapter.QuoteItemViewHolder>() {
    class QuoteItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        lateinit var quote: Quote
        val quoteText = view.findViewById<TextView>(R.id.quote_text)
        val quoteFrom = view.findViewById<TextView>(R.id.quote_from)
        val shareBtn = view.findViewById<Button>(R.id.quote_share_btn)
        val fromSearchBtn = view.findViewById<Button>(R.id.quote_from_search_btn)

        init {
            shareBtn.setOnClickListener{
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TITLE, "Quote to cheer you up")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Quote to cheer you up")
                intent.putExtra(Intent.EXTRA_TEXT, "${quote.text}/nfrom: ${quote.from}")
                intent.type = "text/plain"

                val chooser = Intent.createChooser(intent, "Share quote")
                it.context.startActivity(chooser)
            }

            fromSearchBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https:www.google.com/search?q=${quote.from}"))
                it.context.startActivity(intent)
            }
        }

        fun bind(q: Quote) {
            this.quote = q
            quoteText.text = quote.text
            quoteFrom.text = quote.from

            if(quote.from.isBlank()) {
                fromSearchBtn.visibility = View.GONE
            }
        }
    }

//    parent = RecyclerView object. All View object has 'context'.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent,false)
        return QuoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int) = R.layout.quote_list_item
}