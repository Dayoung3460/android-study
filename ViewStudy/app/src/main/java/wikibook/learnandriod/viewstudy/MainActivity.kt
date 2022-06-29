package wikibook.learnandriod.viewstudy

import android.icu.number.Notation.simple
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(this, R.array.string_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity, "$position $item ${spinner.selectedItem.toString()}", Toast.LENGTH_SHORT).show()
            }
        }

        val metrics = resources.displayMetrics
        val density = metrics.density
        Log.d("mytag", "$density")

        val sizeInDP = 100
        val DPToPX = (sizeInDP * density).toInt()
        val PXToDP = (DPToPX / density).toInt()

//        widthPixels: width of device
        val widthInDP = metrics.widthPixels / density
        val heightInDP = metrics.heightPixels / density
        Log.d("mytag", "$widthInDP x $heightInDP")


    }
}