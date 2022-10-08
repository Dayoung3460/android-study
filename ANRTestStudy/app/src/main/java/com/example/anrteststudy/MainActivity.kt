package com.example.anrteststudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.lang.Math.sqrt
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anr_text_activity)
        findViewById<Button>(R.id.btn).setOnClickListener {
            Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.anr).setOnClickListener {
            Thread(Runnable {
                for(i in 1 .. Int.MAX_VALUE) {
                    Log.d("mytag", sqrt(Random.nextDouble()).toString())
                }
            }).start()

        }
    }
}