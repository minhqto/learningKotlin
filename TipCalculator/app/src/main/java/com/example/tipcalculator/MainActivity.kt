package com.example.tipcalculator

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
           this.calculateTip()
        }

    }

    private fun calculateTip() {
        if (binding.costOfService.text.toString().trim().isEmpty()){
            Toast.makeText(applicationContext, "No service cost was entered", Toast.LENGTH_SHORT).show()
        }else {
            val serviceCost = binding.costOfService.text.toString().toDouble()

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            var tip = serviceCost * tipPercentage
            if (binding.roundUpTip.isChecked) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
        }
   }

}