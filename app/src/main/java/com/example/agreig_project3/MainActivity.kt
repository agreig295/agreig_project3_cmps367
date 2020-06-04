// Alyssa Greig
// CMPS 367
// Project 3
// June 03, 2020

package com.example.agreig_project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val calculator = Calculator()                                                                   // for calling the Calculator class
    var currentTypeDisplay: String = ""                                                             // for displaying characters that the user types into the calculator
    var currentNumber: String = ""                                                                  // for keeping track of the current number the user is typing
    var negative: Boolean = false                                                                   // for keeping track of whether the number the user is typing is negative
    var haveOperation: Boolean = false                                                              // for keeping track of whether the user has already entered an operation into the current calculation
    var answer: String = ""                                                                         // for holding the string version of the answer the calculator generates
    var savedCalculationDisplay: String = ""                                                        // for saving the calculation display when the app is stopped

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "onCreate")
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null) {
            currentTypeDisplay = savedInstanceState.getString("cTD").toString()
            currentNumber = savedInstanceState.getString("cN").toString()
            negative = savedInstanceState.getBoolean("n")
            haveOperation = savedInstanceState.getBoolean("hO")                                // for restoring all the calculator variables
            calculator.value1 = savedInstanceState.getDouble("v1")
            calculator.value2 = savedInstanceState.getDouble("v2")
            savedCalculationDisplay = savedInstanceState.getString("sCD").toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart")

        calculationDisplay.setText(savedCalculationDisplay)                                         // for displaying the saved calculation display on restart
        typeDisplay.setText(currentTypeDisplay + currentNumber)                                     // for displaying the saved type display on restart

        zeroButton.setOnClickListener {
            if(currentNumber != "0") {
                currentNumber += "0"
                typeDisplay.setText(currentTypeDisplay + currentNumber)
            }
        }                                                                                           // all number buttons update currentNumber and redisplay it
        oneButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "1"
            else
                currentNumber += "1"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        twoButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "2"
            else
                currentNumber += "2"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        threeButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "3"
            else
                currentNumber += "3"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        fourButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "4"
            else
                currentNumber += "4"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        fiveButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "5"
            else
                currentNumber += "5"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        sixButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "6"
            else
                currentNumber += "6"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        sevenButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "7"
            else
                currentNumber += "7"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        eightButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "8"
            else
                currentNumber += "8"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        nineButton.setOnClickListener {
            if(currentNumber == "0")
                currentNumber = "9"
            else
                currentNumber += "9"
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        decimalButton.setOnClickListener {
            if (currentNumber == "")
                currentNumber = "0."
            else
                currentNumber += "."
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        negateButton.setOnClickListener {
            if(negative) {
                negative = false
                if(currentTypeDisplay.length != 1)
                    currentTypeDisplay = currentTypeDisplay.substring(0, currentTypeDisplay.length - 2)
                else                                                                                // to avoid displaying multiple negative signs
                    currentTypeDisplay = ""
            }
            else {
                negative = true
                currentTypeDisplay += "-"
            }
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        clearButton.setOnClickListener {
            currentTypeDisplay = ""
            currentNumber = ""                                                                      // to reset every variable
            haveOperation = false
            negative = false
            typeDisplay.setText(currentTypeDisplay)
        }
        addButton.setOnClickListener {
            if(!haveOperation) {                                                                    // for adding an addition sign when the user has not yet entered an operation
                haveOperation = true
                calculator.value1 = currentNumber.toDouble()
                if (negative)
                    calculator.value1 *= -1
                calculator.operation = "+"
                currentTypeDisplay += currentNumber + " + "
            }
            else {
                if(currentNumber == "") {                                                           // for changing the current operation sign
                    currentTypeDisplay = currentTypeDisplay.substring(0, currentTypeDisplay.length - 2) + "+ "
                    calculator.operation = "+"
                }
                else {                                                                              // for solving the current calculation before adding another operation
                    calculator.value2 = currentNumber.toDouble()
                    if (negative)
                        calculator.value2 *= -1
                    calculator.calculate()
                    answer = (calculator.answer).toString()
                    if (answer.substring(answer.length - 2) == ".0")
                        answer = answer.substring(0, answer.length - 2)
                    savedCalculationDisplay = currentTypeDisplay + currentNumber + " = " + answer
                    calculationDisplay.setText(savedCalculationDisplay)
                    calculator.value1 = calculator.answer
                    calculator.value2 = 0.0
                    calculator.operation = "+"
                    currentTypeDisplay = answer + " + "
                }
            }
            currentNumber = ""                                                                      // for resetting the variables for the next number the user enters
            negative = false
            typeDisplay.setText(currentTypeDisplay)
        }
        subtractButton.setOnClickListener {
            if(!haveOperation) {                                                                    // for adding a subtraction sign when the user has not yet entered an operation
                haveOperation = true
                calculator.value1 = currentNumber.toDouble()
                if (negative)
                    calculator.value1 *= -1
                calculator.operation = "-"
                currentTypeDisplay += currentNumber + " - "
            }
            else {
                if(currentNumber == "") {                                                           // for changing the current operation sign
                    currentTypeDisplay = currentTypeDisplay.substring(0, currentTypeDisplay.length - 2) + "- "
                    calculator.operation = "-"
                }
                else {                                                                              // for solving the current calculation before adding another operation
                    calculator.value2 = currentNumber.toDouble()
                    if (negative)
                        calculator.value2 *= -1
                    calculator.calculate()
                    answer = (calculator.answer).toString()
                    if (answer.substring(answer.length - 2) == ".0")
                        answer = answer.substring(0, answer.length - 2)
                    savedCalculationDisplay = currentTypeDisplay + currentNumber + " = " + answer
                    calculationDisplay.setText(savedCalculationDisplay)
                    calculator.value1 = calculator.answer
                    calculator.value2 = 0.0
                    calculator.operation = "-"
                    currentTypeDisplay = answer + " - "
                }
            }
            currentNumber = ""                                                                      // for resetting the variables for the next number the user enters
            negative = false
            typeDisplay.setText(currentTypeDisplay)
        }
        multiplyButton.setOnClickListener {
            if(!haveOperation) {                                                                    // for adding a multiplication sign when the user has not yet entered an operation
                haveOperation = true
                calculator.value1 = currentNumber.toDouble()
                if (negative)
                    calculator.value1 *= -1
                calculator.operation = "x"
                currentTypeDisplay += currentNumber + " x "
            }
            else {
                if(currentNumber == "") {                                                           // for changing the current operation sign
                    currentTypeDisplay = currentTypeDisplay.substring(0, currentTypeDisplay.length - 2) + "x "
                    calculator.operation = "x"
                }
                else {                                                                              // for solving the current calculation before adding another operation
                    calculator.value2 = currentNumber.toDouble()
                    if (negative)
                        calculator.value2 *= -1
                    calculator.calculate()
                    answer = (calculator.answer).toString()
                    if (answer.substring(answer.length - 2) == ".0")
                        answer = answer.substring(0, answer.length - 2)
                    savedCalculationDisplay = currentTypeDisplay + currentNumber + " = " + answer
                    calculationDisplay.setText(savedCalculationDisplay)
                    calculator.value1 = calculator.answer
                    calculator.value2 = 0.0
                    calculator.operation = "x"
                    currentTypeDisplay = answer + " x "
                }
            }
            currentNumber = ""                                                                      // for resetting the variables for the next number the user enters
            negative = false
            typeDisplay.setText(currentTypeDisplay)
        }
        divideButton.setOnClickListener {
            if(!haveOperation) {                                                                    // for adding a division sign when the user has not yet entered an operation
                haveOperation = true
                calculator.value1 = currentNumber.toDouble()
                if (negative)
                    calculator.value1 *= -1
                calculator.operation = "/"
                currentTypeDisplay += currentNumber + " / "
            }
            else {
                if(currentNumber == "") {                                                           // for changing the current operation sign
                    currentTypeDisplay = currentTypeDisplay.substring(0, currentTypeDisplay.length - 2) + "/ "
                    calculator.operation = "/"
                }
                else {                                                                              // for solving the current calculation before adding another operation
                    calculator.value2 = currentNumber.toDouble()
                    if (negative)
                        calculator.value2 *= -1
                    calculator.calculate()
                    answer = (calculator.answer).toString()
                    if (answer.substring(answer.length - 2) == ".0")
                        answer = answer.substring(0, answer.length - 2)
                    savedCalculationDisplay = currentTypeDisplay + currentNumber + " = " + answer
                    calculationDisplay.setText(savedCalculationDisplay)
                    calculator.value1 = calculator.answer
                    calculator.value2 = 0.0
                    calculator.operation = "/"
                    currentTypeDisplay = answer + " / "
                }
            }
            currentNumber = ""                                                                      // for resetting the variables for the next number the user enters
            negative = false
            typeDisplay.setText(currentTypeDisplay)
        }
        equalsButton.setOnClickListener {
            if(currentNumber == "")
                currentNumber = "0"
            if(haveOperation) {                                                                     // for sending the second number to the calculator if there is one
                calculator.value2 = currentNumber.toDouble()
                if (negative)
                    calculator.value2 *= -1
            }
            else {                                                                                  // for sending the first number to the calculator
                calculator.value1 = currentNumber.toDouble()
                if (negative)
                    calculator.value1 *= -1
            }
            calculator.calculate()
            answer = (calculator.answer).toString()
            if(answer.substring(answer.length - 2) == ".0")                                // for getting rid of the decimal in whole numbers
                answer = answer.substring(0, answer.length - 2)
            savedCalculationDisplay = currentTypeDisplay + currentNumber + " = " + answer
            calculationDisplay.setText(savedCalculationDisplay)
            calculator.value1 = calculator.answer
            calculator.value2 = 0.0
            haveOperation = false
            if(calculator.answer < 0) {                                                             // for updating the new current number after the answer is calculated
                negative = true
                currentNumber = answer.substring(1)
                currentTypeDisplay = "-"
            }
            else {
                negative = false
                currentNumber = answer
                currentTypeDisplay = ""
            }
            typeDisplay.setText(currentTypeDisplay + currentNumber)
        }
        backspaceButton.setOnClickListener {
            if(!((currentTypeDisplay == "") && (currentNumber == ""))) {                            // to avoid crashing from backspacing too many times
                if ((haveOperation) && (currentNumber == "")) {                                     // for getting rid of the current operation and make the first number editable again
                    haveOperation = false
                    if (currentTypeDisplay.substring(0, 1) == "-")
                        currentTypeDisplay = "-"
                    else
                        currentTypeDisplay = ""
                    currentNumber = (calculator.value1).toString()
                    if (currentNumber.substring(currentNumber.length - 2) == ".0")
                        currentNumber = currentNumber.substring(0, currentNumber.length - 2)
                } else                                                                              // for backspacing one number in the current number
                    currentNumber = currentNumber.substring(0, currentNumber.length - 1)
                typeDisplay.setText(currentTypeDisplay + currentNumber)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("cTD", currentTypeDisplay)
        outState.putString("cN", currentNumber)
        outState.putBoolean("n", negative)
        outState.putBoolean("hO", haveOperation)                                                    // for saving all the calculator variables
        outState.putDouble("v1", calculator.value1)
        outState.putDouble("v2", calculator.value2)
        outState.putString("sCD", savedCalculationDisplay)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop")
    }

}
