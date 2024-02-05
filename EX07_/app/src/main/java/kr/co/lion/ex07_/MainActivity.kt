package kr.co.lion.ex07_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.co.lion.ex07_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            if (!switchHobby.isChecked){
                checkBoxSoccer.isEnabled = false
                checkBoxBaseBall.isEnabled = false
                checkBoxBasketBall.isEnabled = false
            }

            switchHobby.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    checkBoxSoccer.isEnabled = true
                    checkBoxBaseBall.isEnabled = true
                    checkBoxBasketBall.isEnabled = true
                } else {
                    checkBoxSoccer.isEnabled = false
                    checkBoxBaseBall.isEnabled = false
                    checkBoxBasketBall.isEnabled = false
                }
            }

            buttonConfirm.setOnClickListener {
                val idText = inputId.editText?.text?.toString()
                val pwText = inputPw.editText?.text?.toString()
                val nameText = inputName.editText?.text?.toString()

                if (idText.isNullOrBlank() || pwText.isNullOrBlank() || nameText.isNullOrBlank()) {
                    // 하나라도 비어있을 경우 처리
                    Toast.makeText(applicationContext, "입력되지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    // 모든 입력이 채워져 있는 경우
                    textView.text = "아이디: $idText\n"
                    textView.append("비밀번호: $pwText\n")
                    textView.append("이름: $nameText\n")
                }

                if (switchHobby.isChecked){
                    if (checkBoxSoccer.isChecked){
                        textView.append("취미는 축구입니다.\n")
                    }
                    if (checkBoxBasketBall.isChecked){
                        textView.append("취미는 농구입니다.\n")
                    }
                    if (checkBoxBaseBall.isChecked){
                        textView.append("취미는 야구입니다.\n")
                    }
                    if ((!checkBoxSoccer.isChecked) && (!checkBoxBasketBall.isChecked) &&
                         !(checkBoxBaseBall.isChecked)) {
                        textView.append("취미가 없습니다.\n")
                    }
                } else{
                    textView.append("취미가 없습니다.\n")
                }

                if (idText.isNullOrBlank() || pwText.isNullOrBlank() || nameText.isNullOrBlank()) {
                    // 하나라도 비어있을 경우 처리
                    textView.text = ""
                }
            }
        }
    }
}