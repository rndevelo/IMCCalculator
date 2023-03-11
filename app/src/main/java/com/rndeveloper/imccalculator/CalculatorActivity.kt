package com.rndeveloper.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.rndeveloper.imccalculator.databinding.ActivityCalculatorBinding
import com.rndeveloper.imccalculator.viewmodel.CalculatorViewModel

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    private val calculatorViewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        initListeners()

        calculatorViewModel.isMaleSelected.observe(this) { male ->
            viewMale.setCardBackgroundColor(getBackgroundColor(male))
        }
        calculatorViewModel.isFemaleSelected.observe(this) { female ->
            viewFemale.setCardBackgroundColor(getBackgroundColor(female))
        }
        calculatorViewModel.currentWeight.observe(this) { weight ->
            tvWeight.text = weight.toString()
        }
        calculatorViewModel.currentAge.observe(this) { age ->
            tvAge.text = age.toString()
        }
        calculatorViewModel.currentHeight.observe(this) { currentHeight ->
            tvHeight.text = "$currentHeight cm"
        }
    }

    private fun initComponents() {
        viewMale = binding.viewMale
        viewFemale = binding.viewFemale
        tvHeight = binding.tvHeight
        rsHeight = binding.rsHeight
        btnSubtractWeight = binding.btnSubtractWeight
        btnPlusWeight = binding.btnPlusWeight
        tvWeight = binding.tvWeight
        btnSubtractAge = binding.btnSubtractAge
        btnPlusAge = binding.btnPlusAge
        tvAge = binding.tvAge
        btnCalculate = binding.btnCalculate
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            calculatorViewModel.changeGender()
        }
        viewFemale.setOnClickListener {
            calculatorViewModel.changeGender()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            calculatorViewModel.getCurrentHeight(value)
        }
        btnPlusWeight.setOnClickListener {
            calculatorViewModel.plusWeight()
        }
        btnSubtractWeight.setOnClickListener {
            calculatorViewModel.subtractWeight()
        }
        btnPlusAge.setOnClickListener {
            calculatorViewModel.plusAge()
        }
        btnSubtractAge.setOnClickListener {
            calculatorViewModel.subtractAge()
        }
        btnCalculate.setOnClickListener {
            calculatorViewModel.calculateIMC()
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) R.color.purple_200 else R.color.purple_700
        return ContextCompat.getColor(this, colorReference)
    }
}