package kr.co.lion.ex04_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex04_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            buttonResult.setOnClickListener {
                val number1Str = editTextNumber.text.toString()
                val number2Str = editTextNumber2.text.toString()

                if (number1Str.isNotEmpty() && number2Str.isNotEmpty()) {
                    val number1 = number1Str.toInt()
                    val number2 = number2Str.toInt()

                    val operatorId = toggleOperators.checkedButtonId

                    if (operatorId == R.id.toggleButtonPlus) {
                        textViewResult.text = "결과는 ${number1 + number2}입니다"
                    } else if (operatorId == R.id.toggleButtonMinus) {
                        textViewResult.text = "결과는 ${number1 - number2}입니다"
                    } else if (operatorId == R.id.toggleButtonMultiply) {
                        textViewResult.text = "결과는 ${number1 * number2}입니다"
                    } else if (operatorId == R.id.toggleButtonDiv) {
                        if (number2 != 0) {
                            textViewResult.text = "결과는 ${number1 / number2}입니다"
                        } else {
                            textViewResult.text = "0으로 나눌 수 없습니다."
                        }
                    }
                } else {
                    textViewResult.text = "숫자를 모두 입력해주세요."
                }
            }
        }
    }
}