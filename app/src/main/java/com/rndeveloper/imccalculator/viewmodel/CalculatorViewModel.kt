package com.rndeveloper.imccalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rndeveloper.imccalculator.model.GenderType
import com.rndeveloper.imccalculator.model.IMC
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {

    private val _imc = MutableLiveData<IMC>()
    val imc: LiveData<IMC> = _imc

    private lateinit var imcData: IMC

    init {
        resetData()
    }

    fun resetData() {
        imcData = IMC(
            gender = GenderType.MALE,
            height = 120,
            weight = 60,
            age = 30,
            result = 0.0
        )
        _imc.postValue(imcData)
    }

    fun calculateIMC() {
        val df = DecimalFormat("#.##")
        val imc = imcData.weight / (imcData.height.toDouble() / 100 * imcData.height.toDouble() / 100)
        imcData = imcData.copy(result = df.format(imc).toDouble())
        _imc.postValue(imcData)
    }

    fun changeGender(genderType: GenderType) {
        imcData = imcData.copy(gender = genderType)
        _imc.postValue(imcData)
    }

    fun getCurrentHeight(value: Float) {
        imcData = imcData.copy(height = value.toInt())
        _imc.postValue(imcData)
    }

    fun plusWeight() {
        imcData = imcData.copy(weight = imcData.weight.plus(1))
        _imc.postValue(imcData)
    }

    fun subtractWeight() {
        imcData = imcData.copy(weight = imcData.weight.minus(1))
        _imc.postValue(imcData)
    }

    fun plusAge() {
        imcData = imcData.copy(age = imcData.age.plus(1))
        _imc.postValue(imcData)
    }

    fun subtractAge() {
        imcData = imcData.copy(age = imcData.age.minus(1))
        _imc.postValue(imcData)
    }
}
