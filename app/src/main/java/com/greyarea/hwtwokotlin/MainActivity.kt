package com.greyarea.hwtwokotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.greyarea.hwtwokotlin.R.id.editText


const val EXTRA_MESSAGE = "com.greyarea.hwtwokotlin.MESSAGE"
//private Button button  ;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the Send button */
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    fun toastMsg(msg: String) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun displayToastMsgTwo(v: View) {
        toastMsg("Button Two Clicked")
    }

    fun displayToastMsgThree(v: View) {
        toastMsg("Button Three Clicked")
    }

    fun displayToastMsgFour(v: View) {
        toastMsg("Button Four Clicked")
    }


}
