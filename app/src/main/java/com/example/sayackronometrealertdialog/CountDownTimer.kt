package com.example.sayackronometrealertdialog

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.sayackronometrealertdialog.databinding.ActivityCountDownTimerBinding

class CountDownTimer : AppCompatActivity() {
    private lateinit var binding: ActivityCountDownTimerBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCountDownTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //kullanicinin girdigi name değerini alıp ilgii textview içerisinde gösteriyorum.
        sharedPref=this.getSharedPreferences("com.example.sayackronometrealertdialog", MODE_PRIVATE)
        val gelenName=sharedPref.getString("name","")
        binding.nameTextViewCountDownTimer.text=gelenName

        sayaciBaslat()

        binding.backButtonCountDownTimer.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun sayaciBaslat(){
        binding.startButtonCountDownTimer.setOnClickListener {
            object : CountDownTimer(10000,1000) {
                override fun onTick(p0: Long) {
                    binding.textView.text="${p0/1000}"
                    binding.startButtonCountDownTimer.isEnabled=false
                }

                override fun onFinish() {
                    binding.startButtonCountDownTimer.isEnabled=true
                    binding.textView.text="10"
                }

            }
                .start()
        }

    }
}