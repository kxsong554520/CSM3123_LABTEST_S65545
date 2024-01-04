package com.example.mindsharperner
//Task 2 functions calling method
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Task 3 set header
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "MindSharperner"

        //assign the values to their respective id in main activity layout
        val ans: EditText = findViewById(R.id.answer)
        val check: Button = findViewById(R.id.checkButton)
        val level: RadioGroup = findViewById(R.id.levelButton)
        val firstNum: TextView = findViewById(R.id.num1)
        val secondNum: TextView = findViewById(R.id.num2)
        val operation: TextView = findViewById(R.id.operator)
        val point: TextView = findViewById(R.id.pointsValue)


        //to record the selected radio button to determine the level
        level.setOnCheckedChangeListener{buttonView, isChecked ->
        val lvl = when(level.checkedRadioButtonId){
            R.id.i3 -> 1
            R.id.i5 -> 2
            R.id.i7 -> 3
            else -> 0

        }
        firstNum.setText(getRandomNum(lvl).toString()) //set random value for the first number

            //set up random operator
            var randomOperator = (0..3).shuffled().last()

            if (randomOperator == 0){
                operation.setText("+")
            }else if (randomOperator == 1){
                operation.setText("-")
            }else if (randomOperator == 2){
                operation.setText("*")
            }else if (randomOperator == 3){
                operation.setText("/")
            }

        secondNum.setText(getRandomNum(lvl).toString()) //set random value for the second number
        }


        //verify the answer to add mark or deduct mark
        var pointGotten = 0

        check.setOnClickListener(object: View.OnClickListener{
            override fun onClick (view: View?) {
                //if statement to verify if the answer is correct
                if (verifier(operation.text.toString(),
                        ans.text.toString().toInt(),
                        firstNum.text.toString().toInt(),
                        secondNum.text.toString().toInt(),
                        ))  {
                    ++pointGotten //add 1 point if the answer is correct

                }else {
                    --pointGotten //deduct 1 point if the answer is wrong
                }
                point.text = pointGotten.toString()
            }
        })
    }


    //function to get random number for the question
    fun getRandomNum(level: Int) : Int{
        var randomNum = when(level){
            1 -> (0..9).shuffled().last() //generate maximum 1 digit numbers for level i3
            2 -> (0..99).shuffled().last() //generate maximum 2 digit numbers for level i5
            3 -> (0..999).shuffled().last() //generate maximum 3 digit numbers for level i7
            else -> 0
        }
        return randomNum
    }


    //function for verifying answer after operation
    fun verifier(operation: String, ans: Int, firstNum:Int, secondNum:Int) : Boolean{
        var theAnswer = when(operation){
            "+" -> firstNum + secondNum  //assign addition operator
            "-" -> firstNum - secondNum  //assign subtraction operator
            "*" -> firstNum * secondNum  //assign multiply operator
            "/" -> firstNum / secondNum  //assign divide operator
            else -> 0
        }
        return ans == theAnswer
    }
}