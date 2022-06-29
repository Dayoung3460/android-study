package wikibook.learnandriod.lifecyclemethodstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saving_ui_state_demo)
        var numberText = findViewById<TextView>(R.id.number)
        var numberEdit = findViewById<EditText>(R.id.number_edit)
        var increaseButton = findViewById<Button>(R.id.increase)
        var setButton = findViewById<Button>(R.id.set_number)

        num = savedInstanceState?.getInt("num") ?: 0
        numberText.text = num.toString()

        increaseButton.setOnClickListener {
            num = numberText.text.toString().toInt() + 1
            numberText.text = num.toString()
        }

        setButton.setOnClickListener {
            if (numberEdit.getText().toString().replace(" ", "") != "") {
                Log.d("mytag", numberEdit.toString())
                num = numberEdit.text.toString().toInt()
                numberText.text = num.toString()
            }

        }
    }

//    override fun onStart() {
//        super.onStart()
//        Log.d("mytag", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("mytag", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("mytag", "onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("mytag", "onStop")
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("mytag", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("mytag", "onSaveInstanceState")
        outState.putInt("num", num)
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        Log.d("mytag", "onRestoreInstanceState")
//        num = savedInstanceState.getInt("num") ?: 0
//
//        var numberText = findViewById<TextView>(R.id.number)
//        numberText.text = num.toString()
//    }

//    override fun onRestart() {
//        super.onRestart()
//        Log.d("mytag", "onRestart")
//    }
}