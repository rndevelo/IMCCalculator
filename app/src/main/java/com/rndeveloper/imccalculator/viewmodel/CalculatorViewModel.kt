package com.rndeveloper.imccalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {

    private val _isMaleSelected = MutableLiveData(true)
    val isMaleSelected: LiveData<Boolean> = _isMaleSelected

    private val _isFemaleSelected = MutableLiveData(false)
    val isFemaleSelected: LiveData<Boolean> = _isFemaleSelected

    private val _currentWeight = MutableLiveData(70)
    val currentWeight: LiveData<Int> = _currentWeight

    private val _currentAge = MutableLiveData(30)
    val currentAge: LiveData<Int> = _currentAge

    private val _currentHeight = MutableLiveData(120)
    val currentHeight: LiveData<Int> = _currentHeight

    private val _imc = MutableLiveData<Double>()
    val imc: LiveData<Double> = _imc

    fun resetData() {
        _isMaleSelected.value = true
        _isFemaleSelected.value = false
        _currentWeight.value = 70
        _currentAge.value = 30
        _currentHeight.value = 120
    }

    fun calculateIMC() {
//        FIXME: Decimal format crash
        val df = DecimalFormat("0")
        val imc =
            _currentWeight.value!! / (_currentHeight.value!!.toDouble() / 100 * _currentHeight.value!!.toDouble() / 100)
        _imc.postValue(df.format(imc).toDouble())
    }

    fun getCurrentHeight(value: Float) {
        val df = DecimalFormat("#.##")
        _currentHeight.postValue(df.format(value).toInt())
    }

    fun changeGender() {
        _isMaleSelected.postValue(!isMaleSelected.value!!)
        _isFemaleSelected.postValue(!isFemaleSelected.value!!)
    }

    fun plusWeight() {
        _currentWeight.postValue(currentWeight.value?.plus(1))
    }

    fun subtractWeight() {
        _currentWeight.postValue(currentWeight.value?.minus(1))
    }

    fun plusAge() {
        _currentAge.postValue(currentAge.value?.plus(1))
    }

    fun subtractAge() {
        _currentAge.postValue(currentAge.value?.minus(1))
    }
}