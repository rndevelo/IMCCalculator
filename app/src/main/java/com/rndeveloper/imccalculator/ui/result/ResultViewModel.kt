package com.rndeveloper.imccalculator.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rndeveloper.imccalculator.model.GenderType
import com.rndeveloper.imccalculator.model.Imc

/**
 * IMC powered by ChatGPT
 * Explanation:
 *
 * The BMI formula is the same for both men and women: BMI = weight / (height * height).
 * We use a different normal range for BMI values for men and women.
 * These ranges are defined using the .. operator to create a range of values,
 * and stored in the normalRange constant.
 * The when expression is used to adjust the BMI value if it is outside the normal range.
 * We use a 10% adjustment factor, which is added or subtracted to the BMI value to bring it within the normal range.
 * The age parameter is used to determine whether to apply the normal range adjustment,
 * based on the age range of 20-60. For ages outside this range, the standard BMI range is used.
 */
class ResultViewModel : ViewModel() {

    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double> = _result

    fun calculateIMC(imc: Imc) {

        val (gender: GenderType, height: Int, weight: Int, age: Int) = imc

        when (gender) {
            GenderType.MALE -> maleImc(weight, height / 100.0, age)
            GenderType.FEMALE -> femaleImc(weight, height / 100.0, age)
        }.let { imcCalculated ->
            _result.postValue(imcCalculated)
        }
    }

    // Function to calculate the BMI of a man
    private fun maleImc(weight: Int, height: Double, age: Int): Double {
        val bmi: Double = weight / (height * height * 1.0)
        val normalRange = 20.0..25.0

        return when {
            age < 20 || age > 60 -> bmi // Use standard BMI range for ages outside 20-60
            bmi < normalRange.start -> {
                // Adjust BMI for men below normal range
                val diff = normalRange.start - bmi
                bmi + (diff * 0.1)
            }

            bmi > normalRange.endInclusive -> {
                // Adjust BMI for men above normal range
                val diff = bmi - normalRange.endInclusive
                bmi - (diff * 0.1)
            }

            else -> bmi // BMI is within normal range
        }
    }

    // Function to calculate the BMI of a woman
    private fun femaleImc(weight: Int, height: Double, age: Int): Double {
        val bmi: Double = weight / (height * height * 1.0)
        val normalRange = 19.0..24.0

        return when {
            age < 20 || age > 60 -> bmi // Use standard BMI range for ages outside 20-60
            bmi < normalRange.start -> {
                // Adjust BMI for women below normal range
                val diff = normalRange.start - bmi
                bmi + (diff * 0.1)
            }

            bmi > normalRange.endInclusive -> {
                // Adjust BMI for women above normal range
                val diff = bmi - normalRange.endInclusive
                bmi - (diff * 0.1)
            }

            else -> bmi // BMI is within normal range
        }
    }
}
