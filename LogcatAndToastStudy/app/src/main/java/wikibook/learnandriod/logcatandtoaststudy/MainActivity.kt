package wikibook.learnandriod.logcatandtoaststudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("tag_name1", "Verbose Message")
        Log.v("tag_name2", "Debug Message")
        Log.v("tag_name3", "Info Message")
        Log.v("tag_name4", "Warning Message")
        Log.v("tag_name5", "Error Message")

//        this: activity object
        var toast: Toast = Toast.makeText(this, "Toast Message(Short)", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER or Gravity.LEFT, 0, 0)
        toast.show()
    }
}