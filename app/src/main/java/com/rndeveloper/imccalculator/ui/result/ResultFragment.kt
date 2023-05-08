package com.rndeveloper.imccalculator.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rndeveloper.imccalculator.R
import com.rndeveloper.imccalculator.databinding.FragmentResultBinding
import com.rndeveloper.imccalculator.model.format

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ResultViewModel by activityViewModels()
    private val safeArgs: ResultFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        FragmentResultBinding.inflate(inflater, container, false).apply {
            _binding = this
            return this.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner) { result ->
            setUI(result.format())
        }

        viewModel.calculateIMC(safeArgs.imc)

        binding.btnRecalculate.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUI(result: Double) = with(binding) {
        tvIMC.text = result.toString()
        when (result) {
            in IMC.Section1.range -> { //Bajo peso
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }

            in IMC.Section2.range -> { //Peso normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }

            in IMC.Section3.range -> { //Sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.peso_sobrepeso
                    )
                )
                tvDescription.text = getString(R.string.description_sobrepeso)
            }

            in IMC.Section4.range -> { //Obesidad
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

    private enum class IMC(val range: ClosedFloatingPointRange<Float>) {
        Section1(0.00f..18.50f),
        Section2(18.51f..24.99f),
        Section3(25.00f..29.99f),
        Section4(30.00f..99.00f)
    }
}
