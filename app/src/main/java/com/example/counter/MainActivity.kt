package com.example.counter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
//  Use lateinit to initialize our views to be used throughout the class, will define them in oncreate1
    private lateinit var numberText: TextView
    private lateinit var numberInput: EditText
    private lateinit var interval: EditText
    private lateinit var summary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Get a reference on the button
        val submitButton: Button = findViewById(R.id.main_activity_bt_submit)
        val randomButton: Button = findViewById(R.id.main_activity_bt_random_number)
        val decrementButton: Button = findViewById(R.id.main_activity_bt_decrement)
        val incrementButton: Button = findViewById(R.id.main_activity_bt_increment)

//      Set the value to our view initialized with lateinit
        numberText = findViewById(R.id.main_activity_tv_number)
        numberInput = findViewById(R.id.main_activity_et_number)
        interval = findViewById(R.id.main_activity_et_interval)
        summary = findViewById(R.id.main_activity_tv_summary)

        submitButton.setOnClickListener { submitNumber() }
        randomButton.setOnClickListener { generateRandomNumber() }
        decrementButton.setOnClickListener { changeNumber("-") }
        incrementButton.setOnClickListener { changeNumber("+") }
    }

//  Submit a number and display it on the text view
    private fun submitNumber() {
//      Get the number from the edit text
        var submittedNumber = numberInput.text.toString()

//      Validate if the input are null, if yes change the starting number into 10 and then change the display text
        if (submittedNumber == "") {
            submittedNumber = "10"
        }
        numberText.text = submittedNumber

//      Clear the editText after submit
        numberInput.setText("")

//      Hide the keyboard
        hideKeyboard()
    }

//  Generate a random number and display it on the text view
    private fun generateRandomNumber() {
//      Generate random number with interval of (-100 to 100)
        val randomNumber = (-100..100).random().toString()
        numberText.text = randomNumber
    }

    private fun changeNumber(operation: String) {
        val currentNumber = numberText.text.toString().toInt()
        var intervalNumber = interval.text.toString()

//      Check if the interval value is null
        if (intervalNumber == "") {
            intervalNumber = "1"
        }

//      Validate the arithmetic operation
        if (operation == "+") {
            val incrementResult = currentNumber + intervalNumber.toInt()
            numberText.text = incrementResult.toString()

            summary.text = "$currentNumber + $intervalNumber = $incrementResult"
        } else {
            val decrementResult = currentNumber - intervalNumber.toInt()
            numberText.text = decrementResult.toString()

//          Update the summary text view
            summary.text = "$currentNumber - $intervalNumber = $decrementResult"
        }
//      Hide the keyboard
        hideKeyboard()
    }


    private fun hideKeyboard() {
//      IMM stands for input method manager
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(numberInput.windowToken, 0)
    }
}