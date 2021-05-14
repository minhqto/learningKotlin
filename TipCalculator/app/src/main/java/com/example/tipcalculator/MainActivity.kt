package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        println("test!")

        //adding these in for a rebase test
        val costOfService: EditText = findViewById(R.id.cost_of_service)
        println(costOfService.toString())

        println("More work!")

    }
}