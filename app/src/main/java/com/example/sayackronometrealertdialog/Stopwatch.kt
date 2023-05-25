package com.example.sayackronometrealertdialog

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sayackronometrealertdialog.databinding.ActivityStopwatchBinding

class Stopwatch : AppCompatActivity() {
    private lateinit var binding: ActivityStopwatchBinding
    var runnable: Runnable= Runnable {  }
    var handler: Handler= Handler(Looper.getMainLooper())
    var time=0
    var gecenTime=0
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStopwatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //kullanicinin girdigi name değerini alıp ilgii textview içerisinde gösteriyorum.
        sharedPref=this.getSharedPreferences("com.example.sayackronometrealertdialog", MODE_PRIVATE)
        val gelenName=sharedPref.getString("name","")
        binding.nameTextViewStopwatch.text=gelenName

        start()
        stop()
        reset()

        binding.backButtonStopwatch.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun start(){
        binding.startButtonStopwatch.setOnClickListener {
            binding.startButtonStopwatch.isEnabled=false
            binding.resetButtonStopwatch.isEnabled=true
            binding.stopButtonStopwatch.isEnabled=true
            runnable= Runnable {
                time++
                binding.textViewStopwatch.text="$time"
                handler.postDelayed(runnable,1000)
            }
            handler.post(runnable)
        }
    }

    private fun stop(){
        binding.stopButtonStopwatch.setOnClickListener {
            binding.startButtonStopwatch.isEnabled=true
            gecenTime=time
            handler.removeCallbacks(runnable)
        }
    }

    private fun reset(){
        binding.resetButtonStopwatch.setOnClickListener {
            binding.startButtonStopwatch.isEnabled=true
            binding.stopButtonStopwatch.isEnabled=false
            binding.resetButtonStopwatch.isEnabled=false
            time=0
            binding.textViewStopwatch.text="0"
            handler.removeCallbacks( runnable)
        }
    }
}