/**
 * Tip calculator that calculates how much tip
 * needs to be given based on the bill amount, tip percentage
 * and the number of people
 * @author Bekhruz Otaev
 * */

package com.example.calcapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.calcapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    /*top level variable for binding*/
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**initialize the binding object to access Views in activity_main.xml*/
        binding = ActivityMainBinding.inflate(layoutInflater)

        /**Set the content view of the activity. Instead of passing the resource ID
         * of the layout, R.layout.activity_main, this specifies the root of
         * the hierarchy of views in my app, binding.root*/
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }

        // Listen to the keyboard press on cost of service text field
        binding.costOfServiceEditText.setOnKeyListener{view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }


    private fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.amazing_service -> 0.20
            R.id.good_service -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)

    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    /**
     * Listens to the keyboard input from the user
     * if the user presses the Enter key,
     * handleKeyEvent hides the keyboard
     * @param view View
     * @param keycode Integer
     * @return true when keyboard is pressed, false otherwise
     * */
    private fun handleKeyEvent(view: View, keycode: Int): Boolean {
        if (keycode==KeyEvent.KEYCODE_ENTER){
            // Hide the keyboard
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            return true
        }
        return false
    }
}

