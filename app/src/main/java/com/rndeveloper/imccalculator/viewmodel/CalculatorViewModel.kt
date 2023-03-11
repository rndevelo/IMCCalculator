package com.rndeveloper.imccalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rndeveloper.imccalculator.model.GenderType
import com.rndeveloper.imccalculator.model.IMC

class CalculatorViewModel : ViewModel() {

    private val _imc = MutableLiveData<IMC>()
    val imc: LiveData<IMC> = _imc

    private var imcData: IMC

    init {
        imcData = IMC(
            gender = GenderType.MALE,
            height = 120f,
            weight = 60,
            age = 30
        )
        _imc.postValue(imcData)
    }

    //    fun resetData() {
//        _isMaleSelected.value = true
//        _isFemaleSelected.value = false
//        _currentWeight.value = 70
//        _currentAge.value = 30
//        _currentHeight.value = 120
//    }
//
//    fun calculateIMC() {
////        FIXME: Decimal format crash
//        val df = DecimalFormat("0")
//        val imc =
//            _currentWeight.value!! / (_currentHeight.value!!.toDouble() / 100 * _currentHeight.value!!.toDouble() / 100)
//        _imc.postValue(df.format(imc).toDouble())
//    }
//
//    fun getCurrentHeight(value: Float) {
//        val df = DecimalFormat("#.##")
//        _currentHeight.postValue(df.format(value).toInt())
//    }
//
    fun changeGender(genderType: GenderType) {
        imcData = imcData.copy(gender = genderType)
        _imc.postValue(imcData)
    }
//
//    fun plusWeight() {
//        _currentWeight.postValue(currentWeight.value?.plus(1))
//    }
//
//    fun subtractWeight() {
//        _currentWeight.postValue(currentWeight.value?.minus(1))
//    }
//
//    fun plusAge() {
//        _currentAge.postValue(currentAge.value?.plus(1))
//    }
//
//    fun subtractAge() {
//        _currentAge.postValue(currentAge.value?.minus(1))
//    }
}