package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //this is equivalent to main in pure Kotlin
        setContentView(R.layout.activity_main)
        val rollDiceButton: Button = findViewById(R.id.button)
        rollDiceButton.setOnClickListener {
            rollDice()
        }

        rollDice()
    }

    private fun rollDice(): Unit{
        val myDice = Dice()
        val diceRollOne = myDice.roll()
        val diceImageOne: ImageView = findViewById(R.id.imageView) //get the imageView

        when(diceRollOne){
            1 -> diceImageOne.setImageResource(R.drawable.dice_1)
            2 -> diceImageOne.setImageResource(R.drawable.dice_2)
            3 -> diceImageOne.setImageResource(R.drawable.dice_3)
            4 -> diceImageOne.setImageResource(R.drawable.dice_4)
            5 -> diceImageOne.setImageResource(R.drawable.dice_5)
            6 -> diceImageOne.setImageResource(R.drawable.dice_6)
        }
        val diceRollTwo = myDice.roll()
        val diceImageTwo: ImageView = findViewById(R.id.imageView2) //get the imageView

        when(diceRollTwo){
            1 -> diceImageTwo.setImageResource(R.drawable.dice_1)
            2 -> diceImageTwo.setImageResource(R.drawable.dice_2)
            3 -> diceImageTwo.setImageResource(R.drawable.dice_3)
            4 -> diceImageTwo.setImageResource(R.drawable.dice_4)
            5 -> diceImageTwo.setImageResource(R.drawable.dice_5)
            6 -> diceImageTwo.setImageResource(R.drawable.dice_6)
            7 -> diceImageTwo.setImageResource(R.drawable.dice_1)
        }

    }
}

class Dice() {
    private val sides: Int = 6
    fun roll(): Int {
        return (1..sides).random()
    }
}