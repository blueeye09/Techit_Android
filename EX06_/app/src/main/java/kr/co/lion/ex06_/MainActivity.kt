package kr.co.lion.ex06_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.lion.ex06_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setEvent()
    }

    private fun setEvent(){
        activityMainBinding.apply {
            //입력 완료 버튼
            buttonInputComplete.setOnClickListener{
                Log.d("MainActivity", "버튼 클릭")

                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toInt()
                val korean = editTextKorean.text.toString().toInt()
                val math = editTextMath.text.toString().toInt()
                val totalScore = korean + math
                val averageScore = totalScore / 2

                textViewShowName.text = "이름: $name"
                textViewShowAge.text = "나이: $age"
                textViewShowKor.text = "국어: $korean"
                textViewShowMath.text = "수학: $math"
                textViewShowTotal.text = "총점: $totalScore"
                textViewShowAvg.text = "평균: $averageScore"
            }
        }
    }
}