package com.ozanyazici.storingdata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ozanyazici.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var sharedPref : SharedPreferences
    private var userAgePref : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //SharedPreferences -> XML -> Key-Value
        sharedPref = this.getSharedPreferences("com.ozanyazici.storingdata", Context.MODE_PRIVATE)

        userAgePref = sharedPref.getInt("age",-1)

        if(userAgePref == -1) {
            binding.textView.text = "Your Age :"
        } else {
            binding.textView.text = "Your Age : ${userAgePref}"
        }
    }

    fun save (view : View) {
        val myAge = binding.nametext.text.toString().toIntOrNull()

        if(myAge != null) {
            binding.textView.text = "Your Age : ${myAge}"
            sharedPref.edit().putInt("age", myAge).apply()
        } else {
            binding.textView.text = "Enter Age with numbers"
        }

    }

    fun delete (view : View) {

        userAgePref = sharedPref.getInt("age", -1)

        if(userAgePref != -1) {
            sharedPref.edit().remove("age").apply()
            binding.textView.text = "Your age : "
        }

        binding.nametext.text.clear()


    }
}