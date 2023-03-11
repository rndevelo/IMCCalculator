package com.rndeveloper.imccalculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rndeveloper.imccalculator.R
import com.rndeveloper.imccalculator.databinding.FragmentResultBinding
import com.rndeveloper.imccalculator.viewmodel.CalculatorViewModel

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private val calculatorViewModel: CalculatorViewModel by activityViewModels()

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentResultBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initListeners()
        calculatorViewModel.imc.observe(viewLifecycleOwner) { result ->
            initUI(result)
        }
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            calculatorViewModel.resetData()
            findNavController().navigate(R.id.action_resultFragment_to_calculatorFragment)
        }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> { //Bajo peso
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 -> { //Peso normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> { //Sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.peso_sobrepeso))
                tvDescription.text = getString(R.string.description_sobrepeso)
            }
            in 30.00..99.00 -> { //Obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.obesidad))
                tvDescription.text = getString(R.string.description_obesidad)
            }
            else -> {//error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.obesidad))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvIMC = binding.tvIMC
        tvResult = binding.tvResult
        tvDescription = binding.tvDescription
        btnRecalculate = binding.btnRecalculate
    }
}