package com.example.fragmentstudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class CurrencyConverterFragment3: Fragment() {
    interface CurrencyCalculationListener {
        fun onCalculate(result: Double, amount: Double, from: String, to: String)
    }

    lateinit var listener: CurrencyConverterFragment3.CurrencyCalculationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(activity is CurrencyConverterFragment3.CurrencyCalculationListener) {
            listener = activity as CurrencyConverterFragment3.CurrencyCalculationListener
        } else {
            throw Exception("CurrencyCalculationListener not defined.")
        }
    }

    private val currencyExchangeMap = mapOf("USD" to 1.0, "EUR" to 0.9, "JPY" to 110.0, "KRW" to 1150.0)
    private fun calculateCurrency(amount: Double, from: String, to: String): Double {
        var USDAmount = if(from != "USD") (amount / currencyExchangeMap[from]!!) else amount
        return currencyExchangeMap[to]!! * USDAmount
    }

    lateinit var fromCurrency: String
    lateinit var toCurrency: String

    companion object {
        fun newInstance(from: String, to: String): CurrencyConverterFragment3 {
            val fragment = CurrencyConverterFragment3()
            val args = Bundle()
            args.putString("from", from)
            args.putString("to", to)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_currency_converter2, container, false)
        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        fromCurrency = arguments?.getString("from", "USD")!!
        toCurrency = arguments?.getString("to", "USD")!!
        view.findViewById<TextView>(R.id.exchange_type).text = "Convert ${fromCurrency} -> ${toCurrency}"

        calculateBtn.setOnClickListener {
            val result = calculateCurrency(amount.text.toString().toDouble(), fromCurrency, toCurrency).toString().toDouble()
            listener.onCalculate(result, amount.text.toString().toDouble(), fromCurrency, toCurrency)
        }
        return view
    }

}