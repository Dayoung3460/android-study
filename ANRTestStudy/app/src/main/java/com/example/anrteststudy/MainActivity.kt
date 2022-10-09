package com.example.anrteststudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.Math.sqrt
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.async_task_test_activity)
//        setContentView(R.layout.anr_text_activity)

        MyAsyncTask(this).execute("Hello", "Android", "AsyncTask")

//        var result = findViewById<TextView>(R.id.result)
//        val progressStatus = findViewById<TextView>(R.id.progress_status)
//        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
//        val progress = findViewById<TextView>(R.id.progress)
//
//        findViewById<Button>(R.id.btn).setOnClickListener {
//            Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()
//        }
//
//        findViewById<Button>(R.id.anr).setOnClickListener {
//            result.text = ""
//            progressStatus.text = "Progressing"
//            progressBar.visibility = View.VISIBLE
//
//            Thread(Runnable {
//                var sum = 0.0
//                var count = 0
//                for(i in 1 until 60) {
//                    sum += sqrt(Random.nextDouble())
//                    Thread.sleep(100)
//                    runOnUiThread {
//                        progress.text = "%.1f".format(((count + 1) / 60.toDouble()) * 100) + "% completed"
//                    }
//                    count++
//                }
//                runOnUiThread {
//                    result.text = sum.toString()
//                    progressStatus.text = "Completed"
//                    progressBar.visibility = View.GONE
//                }
//
//            }).start()
//
//        }
    }
}