package com.rndeveloper.imccalculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rndeveloper.imccalculator.R
import com.rndeveloper.imccalculator.databinding.FragmentCalculatorBinding
import com.rndeveloper.imccalculator.model.GenderType
import com.rndeveloper.imccalculator.viewmodel.CalculatorViewModel

class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCalculatorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() = with(binding) {
        viewMale.setOnClickListener {
            calculatorViewModel.changeGender(GenderType.MALE)
        }
        viewFemale.setOnClickListener {
            calculatorViewModel.changeGender(GenderType.FEMALE)
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

    private fun initObservers() = with(calculatorViewModel) {
        imc.observe(viewLifecycleOwner) { imc ->
            binding.viewMale.setCardBackgroundColor(getSelectedBackgroundColor(imc.gender, GenderType.MALE))
            binding.viewFemale.setCardBackgroundColor(getSelectedBackgroundColor(imc.gender, GenderType.FEMALE))
            binding.tvWeight.text = imc.weight.toString()
            binding.tvAge.text = imc.age.toString()
            binding.tvHeight.text = getString(R.string.fragment_calculator_tvcm, imc.height)
            binding.rsHeight.setValues(imc.height.toFloat())
        }
    }

    private fun getSelectedBackgroundColor(genderSelected: GenderType, genderView: GenderType): Int {
        return if (genderSelected == genderView)
            ContextCompat.getColor(requireContext(), R.color.purple_700)
        else
            ContextCompat.getColor(requireContext(), R.color.purple_200)
    }
}
