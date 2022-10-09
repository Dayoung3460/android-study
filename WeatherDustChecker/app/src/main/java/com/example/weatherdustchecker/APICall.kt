package com.example.weatherdustchecker

import android.os.AsyncTask
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class APICall(val callback: APICall.APICallback): AsyncTask<URL, Void, String>() {
    interface APICallback {
        fun onComplete(result: String)
    }

    override fun doInBackground(vararg params: URL?): String? {
        val url = params.get(0)
        val conn: HttpURLConnection = url?.openConnection() as HttpURLConnection
        conn.connect()

        var body = conn?.inputStream?.bufferedReader().use { it?.readText() }

        conn.disconnect()
        return body.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if(result != null) {
            callback.onComplete(result)
        }
    }
}