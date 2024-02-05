package kr.co.lion.ex05_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import kr.co.lion.ex05_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var str1: String
    lateinit var str2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            editTextText.apply {

                addTextChangedListener {
                    str1 = it.toString()
                }
            }

            editTextText2.apply {
                addTextChangedListener {
                    str2 = it.toString()
                }
            }

            button.apply {
                setOnClickListener {
                    textView4.text = "총합: ${str1.toInt() + str2.toInt()}"
                }
            }

            button2.apply {
                setOnClickListener {
                    textView4.text = "총합: ${str1.toInt() - str2.toInt()}"
                }
            }

            button3.apply {
                setOnClickListener {
                    textView4.text = "총합: ${str1.toInt() * str2.toInt()}"
                }
            }

            button4.apply {
                setOnClickListener {
                    textView4.text = "총합: ${str1.toInt() / str2.toInt()}"
                }
            }
        }
    }
}