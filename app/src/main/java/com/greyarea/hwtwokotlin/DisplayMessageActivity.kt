package com.greyarea.hwtwokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.util.Log

class DisplayMessageActivity : AppCompatActivity() {
    private val message = "Verified logging: "

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)


        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }

        Log.d(message,"Display Message Activity begun onCreate()")

        logRegularMessage()
        logComposedMessage()
    }


    override fun onStart()
    {
        super.onStart()
        Log.d(message, "Display Message Activity begun onStart()")
    }

    override fun onResume()
    {
        super.onResume()
        Log.d(message, "Display Message Activity begun onResume()")
    }

    override fun onStop()
    {
        super.onStop()
        Log.d(message, "Display Message Activity begun onStop()")
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(message, "Display Message Activity begun onDestroy()")
    }

    override fun onPause()
    {
        super.onPause()
        Log.d(message, "Display Message Activity begun onPause()")
    }


    // Additional Debug Methods Below
    private fun logRegularMessage()
    {
        logD { "Regular message" }
    }

    private fun logComposedMessage()
    {
        logD { "Composed " + expensiveToCompute() }
    }

    private fun expensiveToCompute(): String
    {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return "result"
    }

    inline fun logD(messageLambda: () -> String)
    {
        if (BuildConfig.DEBUG) {
            val d = Log.d("TAG", messageLambda())
        }
    }
}

