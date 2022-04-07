package com.example.therecipeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.therecipeapp.databinding.ActivityConvertIngredientsBinding
import kotlin.math.ceil
import kotlin.math.round

var selectedIngredient: String? = null
var from: String? = null
var to: String? = null

class ConvertIngredients : AppCompatActivity() {
    private lateinit var binding: ActivityConvertIngredientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConvertIngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIngredientsList()
    }


    override fun onResume() {
        super.onResume()
        getIngredientsList()
    }

    private fun getIngredientsList() {
        val ingredientsType = resources.getStringArray(R.array.IngredientTypes)
        val arrayAdapter = ArrayAdapter(this, R.layout.ingridients_dropdown_items, ingredientsType)
        binding.dropdownMenuIngredientType.setAdapter(arrayAdapter)

        binding.dropdownMenuIngredientType.setOnItemClickListener { adapterView, view, i, l ->
            selectedIngredient = arrayAdapter.getItem(i).toString()
            getMeasurementItem()

        }
    }


    private fun convertIngredients() {
        water()
        butter()
        honey()


    }



    private fun getMeasurementItem() {

        if (selectedIngredient == "Water") {

            val measurement = resources.getStringArray(R.array.waterMeasurement)
            val measurementAdapter =
                ArrayAdapter(this, R.layout.measurement_dropdown_items, measurement)
            binding.dropdownMenuFrom.setAdapter(measurementAdapter)
            binding.dropdownMenuTo.setAdapter(measurementAdapter)

            //from
            binding.dropdownMenuFrom.setOnItemClickListener { adapterView, view, i, l ->
                from = measurementAdapter.getItem(i).toString()

            }

            //to
            binding.dropdownMenuTo.setOnItemClickListener { adapterView, view, i, l ->
                to = measurementAdapter.getItem(i).toString()

               checkConversion()

            }

        } else {

            val measurement = resources.getStringArray(R.array.measurement)
            val measurementAdapter =
                ArrayAdapter(this, R.layout.measurement_dropdown_items, measurement)
            binding.dropdownMenuFrom.setAdapter(measurementAdapter)
            binding.dropdownMenuTo.setAdapter(measurementAdapter)

            //from
            binding.dropdownMenuFrom.setOnItemClickListener { adapterView, view, i, l ->
                from = measurementAdapter.getItem(i).toString()

            }

            //to
            binding.dropdownMenuTo.setOnItemClickListener { adapterView, view, i, l ->
                to = measurementAdapter.getItem(i).toString()

              checkConversion()
            }
        }

    }


    private fun checkConversion() {

        if(from == to){
            binding.fromInputLayout.error = "Can't be the same"
            binding.toInputLayout.error = "Can't be the same"
            Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
            binding.convertedTV.visibility = View.INVISIBLE
        }else{
            binding.fromInputLayout.error = null
            binding.fromInputLayout.error = null

            convertIngredients()
        }

    }

//conversion calculation

    private fun honey() {
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted : Double? = null
        if(selectedIngredient.equals("Honey")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 7.08
                        binding.convertedTV.text = "$converted Grams"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }


            //tablespoon
            if (from.equals("tbsp")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 3
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.062
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 21.25
                        binding.convertedTV.text = "$converted Grams"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //cups
            if (from.equals("Cups")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 48
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 340
                        binding.convertedTV.text = "$converted Grams"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //grams
            if (from.equals("Grams")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 0.14
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.05
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }

    private fun butter() {
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted : Double? = null
        if(selectedIngredient.equals("Butter")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 4.73
                        binding.convertedTV.text = "$converted Grams"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }


            //tablespoon
            if (from.equals("tbsp")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 3
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0616
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 14.18
                        binding.convertedTV.text = "$converted Grams"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //cups
            if (from.equals("Cups")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 48
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 227
                        binding.convertedTV.text = "$converted Grams"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //grams
            if (from.equals("Grams")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 0.21
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.07
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }

    private fun water() {
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted : Double? = null
        if(selectedIngredient.equals("Water")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0213
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Ml") -> {
                        converted = amount.toDouble() * 4.93
                        binding.convertedTV.text = "$converted Ml"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }


            //tablespoon
            if (from.equals("tbsp")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 3
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0616
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Ml") -> {
                        converted = amount.toDouble() * 14.79
                        binding.convertedTV.text = "$converted Ml"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //cups
            if (from.equals("Cups")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 48.69
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16.23
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Ml") -> {
                        converted = amount.toDouble() * 240
                        binding.convertedTV.text = "$converted Ml"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            //ml
            if (from.equals("Ml")) {

                when {
                    to.equals("tsp") -> {
                        converted = amount.toDouble() * 0.203
                        binding.convertedTV.text = "$converted tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.068
                        binding.convertedTV.text = "$converted tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        binding.convertedTV.text = "$converted Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.convertedTV.visibility = View.INVISIBLE
                        Toast.makeText(this, "Invalid Conversion", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }



}