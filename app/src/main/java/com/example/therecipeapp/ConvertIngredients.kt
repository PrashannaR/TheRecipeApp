package com.example.therecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.therecipeapp.databinding.ActivityConvertIngredientsBinding

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
        //getMeasurementItem()

    }


    override fun onResume() {
        super.onResume()
        getIngredientsList()
        //getMeasurementItem()
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

            }
        }

    }



}