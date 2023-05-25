package com.example.sayackronometrealertdialog

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.sayackronometrealertdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*kayitliVeri değişkeni boş değilse yani daha önceden name değeri kayıtlı ise
        uygulama açıldığında kayıtlı isim edittext bölümümde yazılı olacak.*/
        sharedPref=this.getSharedPreferences("com.example.sayackronometrealertdialog", MODE_PRIVATE)
        val kayitliVeri=sharedPref.getString("name","")
        if (kayitliVeri!=""){
            binding.editText.setText(kayitliVeri)
        }

        nextButton()
        deleteName()
    }

    private fun nextButton(){
        binding.nextButton.setOnClickListener {
            val name=binding.editText.text.toString()
            if (name.isNotEmpty()){
                radioButton()
            }else{
                val uyariMesaji=AlertDialog.Builder(this)
                uyariMesaji.setMessage("Adınızı Giriniz!")
                uyariMesaji.setNeutralButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->
                    //ok butonuna tıklayınca dialog kapanacak.
                })
                    .show()
            }
            sharedPref.edit().putString("name",name).apply()
        }
    }

    private fun radioButton(){
        val secilenButton=binding.radioButtons.checkedRadioButtonId
        if (secilenButton==R.id.countDownTimer){
            val intent=Intent(this,CountDownTimer::class.java)
            startActivity(intent)

        }else{
            val intent=Intent(this,Stopwatch::class.java)
            startActivity(intent)
        }
    }

    private fun deleteName(){
        binding.deleteButton.setOnClickListener {
            sharedPref.edit().remove("name").apply()
            binding.editText.setText("")
        }
    }
}