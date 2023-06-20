package com.rndeveloper.imccalculator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class Imc(
    val gender: GenderType,
    val height: Int,
    val weight: Int,
    val age: Int,
    val result: Double
) : Parcelable {

    constructor() : this(
        gender = GenderType.MALE,
        height = 120,
        weight = 60,
        age = 30,
        result = 0.0
    )
}

enum class GenderType {
    MALE,
    FEMALE
}

fun Double.format(): String = DecimalFormat("#.##").run {
    format(this@format)
}