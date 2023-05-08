package com.rndeveloper.imccalculator.ui.calculator

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

class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private val viewModel: CalculatorViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentCalculatorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        setListeners()
    }

    private fun setObservers() = with(binding) {
        viewModel.imc.observe(viewLifecycleOwner) { imc ->
            viewMale.setCardBackgroundColor(getSelectedBackgroundColor(imc.gender, GenderType.MALE))
            viewFemale.setCardBackgroundColor(getSelectedBackgroundColor(imc.gender, GenderType.FEMALE))
            tvWeight.text = imc.weight.toString()
            tvAge.text = imc.age.toString()
            tvHeight.text = getString(R.string.fragment_calculator_tvcm, imc.height)
            rsHeight.setValues(imc.height.toFloat())
        }
    }

    private fun setListeners() = with(binding) {
        // Gender
        viewMale.setOnClickListener {
            viewModel.changeGender(GenderType.MALE)
        }
        viewFemale.setOnClickListener {
            viewModel.changeGender(GenderType.FEMALE)
        }

        // Height
        rsHeight.addOnChangeListener { _, value, _ ->
            viewModel.setCurrentHeight(value.toInt())
        }

        // Weight
        btnPlusWeight.setOnClickListener {
            viewModel.changeWeight(binding.tvWeight.text.toString().toInt().inc())
        }
        btnSubtractWeight.setOnClickListener {
            viewModel.changeWeight(binding.tvWeight.text.toString().toInt().dec())
        }

        // Age
        btnPlusAge.setOnClickListener {
            viewModel.changeAge(binding.tvAge.text.toString().toInt().inc())
        }
        btnSubtractAge.setOnClickListener {
            viewModel.changeAge(binding.tvAge.text.toString().toInt().dec())
        }

        // Button Calculate
        btnCalculate.setOnClickListener {
            CalculatorFragmentDirections.actionCalculatorFragmentToResultFragment(viewModel.imc.value!!).let { action ->
                findNavController().navigate(action)
            }
        }
    }

    private fun getSelectedBackgroundColor(genderSelected: GenderType, genderView: GenderType): Int {
        return if (genderSelected == genderView)
            ContextCompat.getColor(requireContext(), R.color.purple_200)
        else
            ContextCompat.getColor(requireContext(), R.color.purple_700)
    }
}
