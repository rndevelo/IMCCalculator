package com.rndeveloper.imccalculator.model

data class IMC(
    val gender: GenderType,
    val height: Float,
    val weight: Int,
    val age: Int
)

enum class GenderType {
    MALE,
    FEMALE
}