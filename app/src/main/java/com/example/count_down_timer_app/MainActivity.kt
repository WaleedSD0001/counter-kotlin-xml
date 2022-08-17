package com.example.count_down_timer_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    val TIME_IN_MILLIS_SECONDS:Long = 25 * 60 * 1000
    var isRunning: Boolean = false

    lateinit var countDown: TextView
    lateinit var time: TextView
    lateinit var startButton: Button
    lateinit var reset: TextView
    lateinit var progressBar: ProgressBar

    var remaining: Long = TIME_IN_MILLIS_SECONDS
    var counter: CountDownTimer?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countDown = findViewById(R.id.countDown)
        time = findViewById(R.id.time)
        startButton = findViewById(R.id.start_button)
        reset = findViewById(R.id.reset)
        progressBar = findViewById(R.id.progressBar)

        startButton.setOnClickListener {
            if(isRunning == false) {
                startCounting()
                isRunning = true
            }
        }
        reset.setOnClickListener {
            remaining = TIME_IN_MILLIS_SECONDS
            isRunning = false
            counter?.cancel()
            time.text = resources.getText(R.string.time)
            progressBar.progress = 100
        }
    }

    private fun startCounting() {
            counter = object : CountDownTimer(TIME_IN_MILLIS_SECONDS, 1000) {
            override fun onTick(timeLeft: Long) {
                remaining = timeLeft
                updateCounter()
                progressBar.progress = remaining.toDouble().div(TIME_IN_MILLIS_SECONDS.toDouble()).times(100).toInt()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Done", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun updateCounter() {
        val minutes = (remaining.div(1000)).div(60)
        val seconds = (remaining.div(1000)) % 60
        val formattedString = String.format("%02d:%02d", minutes, seconds)

        time.text = formattedString
    }

}