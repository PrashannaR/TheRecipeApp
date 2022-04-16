package com.example.therecipeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.therecipeapp.databinding.ActivityConvertIngredientsBinding
import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.roundToInt

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
        yeast()
        dryGoods()
        breadFlour()
        rolledOats()
        bakingPowder()
        bakingSoda()
        granulatedSugar()
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

            binding.btnConvert.setOnClickListener { convertIngredients() }
        }


        binding.amountInputLayout.setOnClickListener{
            binding.amountInputLayout.setOnKeyListener(View.OnKeyListener{v, keyCode, event ->
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                    convertIngredients()
                    return@OnKeyListener true
                }
                false
            })
        }

    }

//conversion calculation

    private fun yeast(){

            val amount = binding.amountInputLayout.editText?.text.toString()
            var converted: Double? = null


            if(selectedIngredient.equals("Yeast")) {
                //teaspoon
                if (from.equals("tsp")) {

                    when {
                        to.equals("tbsp") -> {
                            converted = amount.toDouble() * 0.33
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round tbsp"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("Cups") -> {
                            converted = amount.toDouble() * 0.021
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round Cups"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("Grams") -> {
                            converted = amount.toDouble() * 3.14
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round Grams"
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
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round tsp"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("Cups") -> {
                            converted = amount.toDouble() * 0.062
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round Cups"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("Grams") -> {
                            converted = amount.toDouble() * 9.44
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round Grams"
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
                            converted = amount.toDouble() * 845
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round tsp"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("tbsp") -> {
                            converted = amount.toDouble() * 285
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round tbsp"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("Grams") -> {
                            converted = amount.toDouble() * 2662.8
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round Grams"
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
                            converted = amount.toDouble() * 0.375
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round tsp"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("tbsp") -> {
                            converted = amount.toDouble() * 0.125
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round tbsp"
                            binding.convertedTV.visibility = View.VISIBLE
                        }
                        to.equals("Cups") -> {
                            converted = amount.toDouble() * 0.005
                            val round = (converted * 100.0).roundToInt() / 100.0
                            binding.convertedTV.text = "$round Cups"
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


    private fun granulatedSugar(){
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted: Double? = null


        if(selectedIngredient.equals("Granulated White Sugar")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 4.16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.062
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 12.5
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 200
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        converted = amount.toDouble() * 0.24
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.08
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.005
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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

    private fun bakingSoda(){
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted: Double? = null

        if(selectedIngredient.equals("Baking Soda")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 4.60
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.062
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 14
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 220
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        converted = amount.toDouble() * 0.22
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.072
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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

    private fun bakingPowder() {

        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted: Double? = null

        if(selectedIngredient.equals("Baking Powder")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 5
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.062
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 14
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 230
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.05
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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


    private fun rolledOats() {
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted: Double? = null

        if(selectedIngredient.equals("Rolled Oats")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 1.88
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.062
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 5.63
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 90
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        converted = amount.toDouble() * 0.53
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.18
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.01
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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

    private fun breadFlour() {

    val amount = binding.amountInputLayout.editText?.text.toString()
    var converted: Double? = null

    if(selectedIngredient.equals("Bread Flour")) {
        //teaspoon
        if (from.equals("tsp")) {

            when {
                to.equals("tbsp") -> {
                    converted = amount.toDouble() * 0.33
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round tbsp"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("Cups") -> {
                    converted = amount.toDouble() * 0.021
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round Cups"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("Grams") -> {
                    converted = amount.toDouble() * 2.65
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round Grams"
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
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round tsp"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("Cups") -> {
                    converted = amount.toDouble() * 0.062
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round Cups"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("Grams") -> {
                    converted = amount.toDouble() * 7.94
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round Grams"
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
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round tsp"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("tbsp") -> {
                    converted = amount.toDouble() * 16
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round tbsp"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("Grams") -> {
                    converted = amount.toDouble() * 136
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round Grams"
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
                    converted = amount.toDouble() * 0.38
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round tsp"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("tbsp") -> {
                    converted = amount.toDouble() * 0.13
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round tbsp"
                    binding.convertedTV.visibility = View.VISIBLE
                }
                to.equals("Cups") -> {
                    converted = amount.toDouble() * 0.01
                    val round = (converted * 100.0).roundToInt() / 100.0
                    binding.convertedTV.text = "$round Cups"
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


    private fun dryGoods() {
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted: Double? = null

        if(selectedIngredient.equals("Dry Goods")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 2.6
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.062
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 8
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 128
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.05
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0078
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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

    private fun honey() {
        val amount = binding.amountInputLayout.editText?.text.toString()
        var converted : Double? = null
        if(selectedIngredient.equals("Honey")) {
            //teaspoon
            if (from.equals("tsp")) {

                when {
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.33
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 7.08
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.062
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 21.25
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 340
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.05
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.021
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 4.73
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0616
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 14.18
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Grams") -> {
                        converted = amount.toDouble() * 227
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Grams"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.07
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0213
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Ml") -> {
                        converted = amount.toDouble() * 4.93
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Ml"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0616
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Ml") -> {
                        converted = amount.toDouble() * 14.79
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Ml"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 16.23
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Ml") -> {
                        converted = amount.toDouble() * 240
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Ml"
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
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("tbsp") -> {
                        converted = amount.toDouble() * 0.068
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round tbsp"
                        binding.convertedTV.visibility = View.VISIBLE
                    }
                    to.equals("Cups") -> {
                        converted = amount.toDouble() * 0.0042
                        val round = (converted * 100.0).roundToInt() / 100.0
                        binding.convertedTV.text = "$round Cups"
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