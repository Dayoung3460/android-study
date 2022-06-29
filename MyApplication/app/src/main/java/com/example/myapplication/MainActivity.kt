package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_layout)

        var myTextView: TextView = findViewById(R.id.my_textview)
        var myEditText = findViewById<EditText>(R.id.my_edittext)
        var myButton = findViewById<Button>(R.id.my_btn)

        myButton.setOnClickListener {
            var btnLabel = (it as Button).text
            Toast.makeText(applicationContext, "Button label: $btnLabel", Toast.LENGTH_LONG).show()
            }
        }


}