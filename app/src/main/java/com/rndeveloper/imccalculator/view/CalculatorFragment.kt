package com.rndeveloper.imccalculator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.rndeveloper.imccalculator.R
import com.rndeveloper.imccalculator.databinding.FragmentCalculatorBinding
import com.rndeveloper.imccalculator.viewmodel.CalculatorViewModel

class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by activityViewModels()

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCalculatorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initListeners()
        initObservers()
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
            findNavController().navigate(R.id.action_calculatorFragment_to_resultFragment)
        }
    }

    private fun initObservers() {
        calculatorViewModel.isMaleSelected.observe(viewLifecycleOwner) { male ->
            viewMale.setCardBackgroundColor(getBackgroundColor(male))
        }
        calculatorViewModel.isFemaleSelected.observe(viewLifecycleOwner) { female ->
            viewFemale.setCardBackgroundColor(getBackgroundColor(female))
        }
        calculatorViewModel.currentWeight.observe(viewLifecycleOwner) { weight ->
            tvWeight.text = weight.toString()
        }
        calculatorViewModel.currentAge.observe(viewLifecycleOwner) { age ->
            tvAge.text = age.toString()
        }
        calculatorViewModel.currentHeight.observe(viewLifecycleOwner) { currentHeight ->
            tvHeight.text = "$currentHeight cm"
        }
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) R.color.purple_200 else R.color.purple_700
        return ContextCompat.getColor(requireContext(), colorReference)
    }
}