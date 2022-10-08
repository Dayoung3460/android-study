package com.example.anrteststudy

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.view.View

class MyAsyncTask (val activity: Activity): AsyncTask<String, Int, String>() {
    lateinit var progressBar: ProgressBar
    lateinit var completedTask: TextView
    lateinit var resultText: TextView
    private var completedTaskCount = 0

    override fun onPreExecute() {
        super.onPreExecute()
        Log.d("mytag", "onPreExcute: ${Thread.currentThread().name}")

        progressBar = activity.findViewById<ProgressBar>(R.id.progress)
        completedTask = activity.findViewById<TextView>(R.id.completed_task)
        resultText = activity.findViewById<TextView>(R.id.result_text)

        progressBar.visibility = View.VISIBLE
        completedTask.visibility = View.VISIBLE

    }

    override fun doInBackground(vararg p0: String?): String {
        var ret = ""
        if(p0.isNotEmpty()) {
            for(s in p0) {
                for((idx, c) in s!!.withIndex()) {
                    Thread.sleep(250)
                    var progressCurrent = (((idx + 1).toDouble() / s.length.toDouble()) * 100).toInt()
                    Log.d("mytag", "doInBackground: ${Thread.currentThread().name}")
                    publishProgress(progressCurrent)
                }
                ret += s.reversed() + " "
            }
            return ret
        } else {
            throw Exception("No task to progress")
        }
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        Log.d("mytag", "onProgressUpdate: ${Thread.currentThread().name}")
        if(values[0]!! == 100) {
            completedTask.text = "Completed ${completedTaskCount + 1} tasks"
            completedTaskCount++
        }
        progressBar.progress = values[0]!!
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d("mytag", "onPostExecute: ${Thread.currentThread().name}")
        progressBar.visibility = View.GONE
        completedTask.text = "All completed (Total ${completedTaskCount} tasks)"
        resultText.text = result
        resultText.visibility = View.VISIBLE
    }
}