package kr.co.lion.android26_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.android26_startactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 계약 객체
        // 다른 Activity를 갔다 돌아왔을 때의 계약
        val contract = ActivityResultContracts.StartActivityForResult()

        // 계약을 등록한다.
        // 다른 Activity를 갔다 돌아오면 { } 내의 코드가 동작한다.
        val fourthActivityLauncher = registerForActivityResult(contract){
            // ForthActivity를 갔다 돌아왔을 때 동작할 코드를 작성한다.
            activityMainBinding.textViewMain.text = "ForthActivity를 갔다 돌아왔습니다\n"

            if(it != null){
                // 작업의 결과로 분기한다.
                when(it.resultCode){
                    RESULT_OK -> {
                        // setResult 메서드에 설정한 Intent 객체로 부터 데이터를 추출한다.
                        if(it.data != null){
                            val value1 = it.data!!.getIntExtra("value1", 0)
                            val value2 = it.data!!.getDoubleExtra("value2", 0.0)
                            val value3 = it.data!!.getBooleanExtra("value3", false)

                            activityMainBinding.textViewMain.append("value1 : ${value1}\n")
                            activityMainBinding.textViewMain.append("value2 : ${value2}\n")
                            activityMainBinding.textViewMain.append("value3 : ${value3}\n")
                        }
                    }
                    RESULT_CANCELED -> activityMainBinding.textViewMain.text = "작업이 취소 되었습니다"
                }
            }
        }

        activityMainBinding.apply {
            buttonStartSecond.setOnClickListener {
                // Intent 객체
                // Intent : 안드로이드 4대 구성요소를 실행하기 위해 OS에게 전달하는 요청 정보 객체
                // 실행하고자 하는 것과 관련된 모든 정보들을 담아 OS로 전달하면
                // OS가 이를 보고 실행해준다.
                // 두 번째 매개변수 : 실행하고자 하는 Activity의 자바 클래스를 지정한다.
                val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)

                // 새롭게 실행되는 Activity에 전달할 데이터가 있다면 Intent에 담아준다.
            }
    }
}