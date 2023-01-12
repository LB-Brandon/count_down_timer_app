package com.brandon.countdowntimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.brandon.countdowntimerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    private var countDownTimer : CountDownTimer? = null
    private var timerDuration : Long = 60000
    private var pauseOffset : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvTimer?.text = (timerDuration/1000).toString()

        binding?.btnStart?.setOnClickListener {
            startTimer(pauseOffset)
        }
        binding?.btnPause?.setOnClickListener {
            pauseTimer()
        }
        binding?.btnReset?.setOnClickListener {
            resetTimer()
        }

    }

    private fun startTimer(pauseOffsetL : Long) {
        countDownTimer = object : CountDownTimer(timerDuration - pauseOffsetL, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished
                binding?.tvTimer?.text = (millisUntilFinished / 1000).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Timer is finished", Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    private fun pauseTimer(){
        if(countDownTimer != null){
            countDownTimer!!.cancel()
        }
    }

    private fun resetTimer(){
        if(countDownTimer != null){
            countDownTimer!!.cancel()
            binding?.tvTimer?.text = (timerDuration/1000).toString()
            countDownTimer = null
            pauseOffset = 0
        }
    }


}