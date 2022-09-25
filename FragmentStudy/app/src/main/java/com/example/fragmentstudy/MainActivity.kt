package com.example.fragmentstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, CurrencyConverterFragment2.newInstance("KRW", "USD"))
        transaction.add(R.id.fragment_container, CurrencyConverterFragment2.newInstance("JPY", "KRW"))
        transaction.add(R.id.fragment_container, CurrencyConverterFragment2.newInstance("EUR", "JPY"))
        transaction.commit()
    }
}