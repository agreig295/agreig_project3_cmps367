// Alyssa Greig
// CMPS 367
// Project 3
// June 03, 2020

package com.example.agreig_project3
import kotlin.math.round

class Calculator(var value1: Double = 0.0, var value2: Double = value1, var operation: String? = null) {

    var answer: Double = 0.0

    fun calculate() {
        when (operation) {
            "+" -> answer = round(100000000 * (value1 + value2)) / 100000000
            "-" -> answer = round(100000000 * (value1 - value2)) / 100000000
            "x" -> answer = round(100000000 * (value1 * value2)) / 100000000
            "/" -> answer = round(100000000 * (value1 / value2)) / 100000000
            null -> answer = value1
        }
    }

}

