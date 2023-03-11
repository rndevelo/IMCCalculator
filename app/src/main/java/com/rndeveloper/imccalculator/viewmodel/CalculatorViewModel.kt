package com.rndeveloper.imccalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {

    val isMaleSelected = MutableLiveData(true)
    val isFemaleSelected = MutableLiveData(false)
    val currentWeight = MutableLiveData(70)
    val currentAge = MutableLiveData(30)
    val currentHeight = MutableLiveData(120)
    val imc = MutableLiveData<Double>()

    fun calculateIMC() {
        val df = DecimalFormat("#.##")
        val imc =
            currentWeight.value!! / (currentHeight.value!!.toDouble() / 100 * currentHeight.value!!.toDouble() / 100)
        this.imc.postValue(df.format(imc).toDouble())
    }

    fun getCurrentHeight(value: Float) {
        val df = DecimalFormat("#.##")
        currentHeight.postValue(df.format(value).toInt())
    }

    fun changeGender() {
        isMaleSelected.postValue(!isMaleSelected.value!!)
        isFemaleSelected.postValue(!isFemaleSelected.value!!)
    }

    fun plusWeight() {
        currentWeight.postValue(currentWeight.value?.plus(1))
    }

    fun subtractWeight() {
        currentWeight.postValue(currentWeight.value?.minus(1))
    }

    fun plusAge() {
        currentAge.postValue(currentAge.value?.plus(1))
    }

    fun subtractAge() {
        currentAge.postValue(currentAge.value?.minus(1))
    }
}