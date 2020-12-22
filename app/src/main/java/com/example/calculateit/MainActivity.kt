package com.example.calculateit

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.math.BigInteger
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    // Variables Required
    private var displayResult:TextView? = null
    private var inputDisplay:TextView? = null

    // Store Result
    private var equation:String = "0"
    private var num1: Int = 0
    private var num2:Int = 0
    private var operation:String = ""
    private var tmp:String = ""

    // Res Checkers
    private var isEquating:Boolean = false
    private var trigo:Boolean = false
    private var resCal:Boolean = false
    private var isSin:Boolean = false
    private var isCos:Boolean = false
    private var isTan:Boolean = false
    private var isSqrt:Boolean = false
    private var isSqr:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referncing Displays
        displayResult = findViewById(R.id.Display)
        inputDisplay = findViewById(R.id.InputDisplay)

    }

    // Take input Numbers from User Defined Buttons
    fun inputNum(Num: View) {
        val inputBtn: Button = Num as Button
        // check for sin cos tan operation (Takes only one input)

        if(isSin || isCos || isTan || isSqrt || isSqr)
        {
            equation += inputBtn.text.toString()
            if(isSin)
            {
                displayResult?.text = ("sin ("+equation.toInt()+")");
            }
            else if(isCos)
            {
                displayResult?.text = ("cos ("+equation.toInt()+")");
            }
            else if(isTan)
            {
                displayResult?.text = ("tan ("+equation.toInt()+")");
            }
            else if(isSqrt)
            {
                displayResult?.text = ("sqt ("+equation.toInt()+")");
            }
            else if(isSqr)
            {
                displayResult?.text = ("sqr ("+equation.toInt()+")");
            }

        }
        // Takes two numbers
        else
        {
            if(equation != "0")
            {
                equation += inputBtn.text.toString()
            }
            else
            {
                equation = inputBtn.text.toString()
            }
            displayResult?.text=(tmp+operation+equation)
        }

    }

    // Take operations input
    fun inputOperation(Operation:View) {
        val inputBtn: Button = Operation as Button
        if(!trigo)
        {
            if(!isSin && !isCos && !isTan && !isSqr && !isSqrt)
            {
                if(operation == "")
                {
                    if(equation !="0"){
                        tmp = equation
                        if(equation.length <= 8)
                        {
                            num1 = equation.toInt()
                        }
                        else
                        {
                            invalidInput("Input size reached!")
                            resetEverything()
                            return
                        }
                        equation = "0"

                        operation = inputBtn.text.toString()
                        displayResult?.text = (tmp+operation)
                    }
                    else
                    {
                        operation = inputBtn.text.toString()
                        tmp = equation
                        displayResult?.text = (equation+operation)
                    }
                }
                else
                {
                    operation = inputBtn.text.toString()
                    if(num2 == 0)
                    {
                        displayResult?.text = (num1.toString()+operation+equation)
                    }
                    else
                    {
                        displayResult?.text = (num1.toString()+operation+num2.toString())
                    }
                }
            }
        }
    }

    // equating expression
    fun equate(eq:View) {
        resCal = true
        if(isEquating)
        {
            resetEverything()
        }
        else
            if(isSin)
            {
                val d:Float = sin(equation.toFloat())
                inputDisplay?.setText(displayResult?.text.toString())
                displayResult?.setText(d.toString())
                isSin = false
                isEquating = true;
            }
            else
                if(isCos)
                {
                    val d:Float = cos(equation.toFloat())
                    inputDisplay?.setText(displayResult?.text.toString())
                    displayResult?.setText(d.toString())
                    isCos = false
                    isEquating = true;
                }
                else if(isTan) {
                    val d:Float = tan(equation.toFloat())
                    inputDisplay?.setText(displayResult?.text.toString())
                    displayResult?.setText(d.toString())
                    isTan = false
                    isEquating = true;
                }
                else
                    if(isSqrt)
                    {
                        val d:Float = sqrt(equation.toFloat())
                        inputDisplay?.setText(displayResult?.text.toString())
                        displayResult?.setText(d.toString())
                        isSqrt = false
                        isEquating = true;
                    }
                    else if(isSqr)
                    {
                        val d:Int = Mul(equation.toInt(),equation.toInt())
                        inputDisplay?.setText(displayResult?.text.toString())
                        displayResult?.setText(d.toString())
                        isSqr = false
                        isEquating = true;
                    }
                    else{
                        if(equation != "")
                        {
                            if(equation.length <= 8)
                            {
                                num2 = equation.toInt()
                            }
                            else{
                                invalidInput("Input size reached!")
                                resetEverything()
                                return
                            }

                        }
                        displayRes()
                        when(operation)
                        {
                            "+" -> {
                                val rs: Int = Add(num1, num2)
                                displayResult?.setText(rs.toString())
                                num1 = rs
                                tmp = num1.toString()
                                equation = ""

                            }
                            "-" -> {
                                val rs: Int = Sub(num1, num2)
                                displayResult?.setText(rs.toString())
                                num1 = rs
                                tmp = num1.toString()
                                equation = ""

                            }
                            "x" -> {
                                val rs: Int = Mul(num1, num2)
                                displayResult?.setText(rs.toString())
                                num1 = rs
                                tmp = num1.toString()
                                equation = ""

                            }
                            "/" -> {
                                val rs: Float = Divide(num1, num2)
                                if(rs != 0.0f)
                                {
                                    displayResult?.setText(rs.toString())
                                    num1 = rs.toInt()
                                    tmp = num1.toString()
                                    equation = ""
                                }
                                else
                                {
                                    resetEverything()
                                }
                            }
                        }
                    }
    }

    // Display result on screen
    fun displayRes() {
        inputDisplay?.text = (num1.toString()+operation+num2.toString())
    }

    // Operation Functions
    fun Add(N1:Int,N2:Int): Int {
        return (N1+N2)
    }
    fun Sub(N1:Int,N2:Int): Int {
        return (N1-N2)
    }
    fun Mul(N1:Int,N2:Int):Int {
        return (N1*N2)
    }
    fun Divide(N1:Int,N2:Int): Float {
        if(N2 == 0)
        {
            invalidInput("Divided By 0!")
            displayResult?.text=("Divided By 0!")
            resetEverything()
            return 0.0f;
        }
        else
        {
            val F:Float =  ((N1).toFloat()/(N2).toFloat())
            return F
        }
    }
    fun sqrtVal(tn:View) {
        resetEverything()
        displayResult?.text = ("sqrt()")
        isSqrt = true
        trigo = true
    }
    fun squareVal(tn:View) {
        resetEverything()
        displayResult?.text = ("sqr()")
        isSqr = true
        trigo = true
    }

    // For Invalid Input (msg)
    fun invalidInput(txt:String) {
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show()
    }

    // clear calculator
    fun clear(B:View) {
        resCal = false
        resetEverything()
    }
    fun resetEverything() {
        equation = "0"
        tmp = ""
        num1 = 0
        num2 = 0
        operation = ""
        isSin = false
        isCos = false
        resCal = false
        trigo = false
        isTan = false
        isSqrt = false
        isSqr = false
        isEquating = false
        inputDisplay?.setText("")
        displayResult?.setText("0")
    }

    // Back Space Functionalities
    fun backsp(B:View) {
        var chaneDisp:String = displayResult?.text.toString()
        if(!isSin && !isCos && !isTan && !isSqrt && !isSqr)
        {
            if(resCal)
            {
                resetEverything()
            }
            else
                if(chaneDisp.length <=1)
                {
                    resetEverything()
                }
                else
                    if(chaneDisp.last() == '+' || chaneDisp.last() == '-' || chaneDisp.last() == 'x' || chaneDisp.last() == '/')
                    {
                        chaneDisp = chaneDisp.dropLast(1)
                        operation = ""
                        equation = num1.toString()
                        tmp = ""
                        displayResult?.text = chaneDisp
                        num2 = 0
                    }
                    else if(operation == "")
                    {
                        chaneDisp = chaneDisp.dropLast(1)
                        displayResult?.text = chaneDisp
                        num1 = chaneDisp.toInt()
                        equation = num1.toString()
                        operation = ""
                        tmp = ""
                    }
                    else
                    {
                        chaneDisp = chaneDisp.dropLast(1)
                        var xt:String = "";
                        for(i in chaneDisp)
                        {
                            if(i == '+' || i=='-' || i=='x' || i=='/')
                            {
                                operation=i.toString();
                                num1 = xt.toInt()
                                xt = ""
                            }
                            else
                            {
                                xt+=i.toString()
                            }
                        }
                        if(xt!="")
                        {
                            num2 = xt.toInt()
                            displayResult?.text = (num1.toString()+operation+num2.toString())
                            equation = xt
                        }
                        else
                        {
                            displayResult?.text = (num1.toString()+operation)
                            equation = ""
                        }
                    }
        }
        else
        {
            chaneDisp = chaneDisp.dropLast(1)
            chaneDisp = chaneDisp.drop(5)
            val eq:String = chaneDisp

            if(chaneDisp == "0")
            {
                resetEverything()
            }
            else
                if(eq.length == 1)
                {
                    equation = "0"
                    trigoDisplay(equation)
                }
                else
                {
                    chaneDisp = chaneDisp.dropLast(1)
                    equation = chaneDisp
                    trigoDisplay(equation)
                }

        }
    }

    // Trignometric operations
    fun findSin(tn:View){
        resetEverything()
        displayResult?.text = ("sin ()")
        isSin = true
        trigo = true
    }
    fun findCos(tn:View){
        resetEverything()
        displayResult?.text = ("cos ()")
        isCos = true
        trigo = true
    }
    fun findTan(tn:View){
        resetEverything()
        displayResult?.text = ("tan ()")
        isTan = true
        trigo = true
    }
    fun trigoDisplay(A:String) {
        if(isSin)
        {
            displayResult?.text = ("sin ("+A+")")
        }
        else
            if(isCos)
            {
                displayResult?.text = ("cos ("+A+")")
            }
            else if(isTan)
            {
                displayResult?.text = ("tan ("+A+")")
            }
            else if(isSqrt)
            {
                displayResult?.text = ("sqt ("+A+")")
            }
    }
}