package com.rndeveloper.imccalculator.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rndeveloper.imccalculator.model.GenderType
import com.rndeveloper.imccalculator.model.Imc

class CalculatorViewModel : ViewModel() {

    private val _imc = MutableLiveData<Imc>()
    val imc: LiveData<Imc> = _imc

    init {
        _imc.postValue(Imc())
    }

    fun changeGender(genderType: GenderType) {
        imc.value?.copy(gender = genderType)?.let { imcData ->
            _imc.postValue(imcData)
        }
    }

    fun setCurrentHeight(value: Int) {
        imc.value?.copy(height = value)?.let { imcData ->
            _imc.postValue(imcData)
        }
    }


    fun changeWeight(newValue: Int) {
        imc.value?.copy(weight = newValue)?.let { imcData ->
            _imc.postValue(imcData)
        }
    }

    fun changeAge(newValue: Int) {
        imc.value?.copy(age = newValue)?.let { imcData ->
            _imc.postValue(imcData)
        }
    }
}
