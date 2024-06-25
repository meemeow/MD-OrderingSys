package com.example.clamor_act6

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewOrderSummary = findViewById<TextView>(R.id.textViewOrderSummary)
        val spinnerFoods = findViewById<Spinner>(R.id.spinnerFoods)
        val checkBoxFries = findViewById<CheckBox>(R.id.checkBoxFries)
        val checkBoxMashPotato = findViewById<CheckBox>(R.id.checkBoxMashPotato)
        val checkBoxHashbrown = findViewById<CheckBox>(R.id.checkBoxHashbrown)
        val radioGroupDrinks = findViewById<RadioGroup>(R.id.radioGroupDrinks)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        // Prices map for each category
        val foodPrices = mapOf(
            "Nuggets" to 3.0,
            "Chicken" to 4.0,
            "Carbonara" to 5.0
        )
        val addOnPrices = mapOf(
            "MashPotato" to 1.75,
            "Fries" to 2.0,
            "Hashbrown" to 1.75
        )
        val drinkPrices = mapOf(
            "Coke" to 1.0,
            "Coffee" to 1.25,
            "Water" to 0.75
        )

        buttonSubmit.setOnClickListener {
            val selectedItems = StringBuilder()
            var total = 0.0

            // Handle spinner (food item)
            val selectedFood = spinnerFoods.selectedItem.toString()
            if (selectedFood != "Select Food") {
                val price = foodPrices[selectedFood] ?: 0.0
                selectedItems.append("Main:\n")
                selectedItems.append("$selectedFood = $$price\n")
                total += price
            }

            // Handle checkboxes (add ons)
            selectedItems.append("Add Ons:\n")
            if (checkBoxFries.isChecked) {
                val price = addOnPrices["Fries"] ?: 0.0
                selectedItems.append("Fries = $$price\n")
                total += price
            }
            if (checkBoxMashPotato.isChecked) {
                val price = addOnPrices["MashPotato"] ?: 0.0
                selectedItems.append("MashPotato = $$price\n")
                total += price
            }
            if (checkBoxHashbrown.isChecked) {
                val price = addOnPrices["Hashbrown"] ?: 0.0
                selectedItems.append("Hashbrown = $$price\n")
                total += price
            }

            // Handle radio buttons (drink item)
            val selectedDrinkId = radioGroupDrinks.checkedRadioButtonId
            if (selectedDrinkId != -1) {
                val radioButton = findViewById<RadioButton>(selectedDrinkId)
                val drinkName = radioButton.text.toString()
                val price = drinkPrices[drinkName] ?: 0.0
                selectedItems.append("Drinks:\n")
                selectedItems.append("$drinkName = $$price\n")
                total += price
            }

            // Format the summary with Total
            selectedItems.append("-----------------------\n")
            selectedItems.append("Total = $$total")

            // Display selected items in the summary text view
            textViewOrderSummary.text = selectedItems.toString()
            textViewOrderSummary.visibility = TextView.VISIBLE
        }
    }
}
