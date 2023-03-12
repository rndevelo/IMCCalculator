package com.rndeveloper.imccalculator.model

data class IMC(
    val gender: GenderType,
    val height: Int,
    val weight: Int,
    val age: Int,
    val result: Double
)

enum class GenderType {
    MALE,
    FEMALE
}