package kr.co.lion.ex07_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.ex07_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initView()
        setViewEvent()
    }

    // 화면 요소에 관련된 초기화
    fun initView(){
        // 취미 스위치는 on 상태로 설정한다.
        activityMainBinding.apply {
            switchHobby.isChecked = true
        }
    }

    // 화면 요소에 대한 이벤트 설정
    fun setViewEvent(){
        activityMainBinding.apply {
            // 취미 스위치 이벤트
            switchHobby.setOnCheckedChangeListener { buttonView, isChecked ->
                // 스위치의 체크 상태로 분기한다.
                when(isChecked){
                    // ON 상태라면
                    true -> {
//                        checkBoxHobby1.isVisible = true
//                        checkBoxHobby2.isVisible = true
//                        checkBoxHobby3.isVisible = true
                        // 활성화
                        checkBoxHobby1.isEnabled = true
                        checkBoxHobby2.isEnabled = true
                        checkBoxHobby3.isEnabled = true
                    }
                    // off 상태
                    false -> {
//                        checkBoxHobby1.isVisible = false
//                        checkBoxHobby2.isVisible = false
//                        checkBoxHobby3.isVisible = false
                        // 비활성화
                        checkBoxHobby1.isEnabled = false
                        checkBoxHobby2.isEnabled = false
                        checkBoxHobby3.isEnabled = false
                    }
                }
            }

            // 버튼 이벤트
            buttonSubmit.setOnClickListener {
                // 아이디
                textViewResult.text = "아이디: ${textFieldUserId.text}\n"
                //비밀번호
                textViewResult.append("비밀번호: ${textFiledUserPw.text}\n")
                // 사용자 이름
                textViewResult.append("이름 ${textFieldUserName.text}\n")

                // 스위치의 on/off 상태에 따라 분기한다.
                when(switchHobby.isChecked){
                    // off 상태라면 취미가 없는 것으로 취급한다.
                    false -> textViewResult.append("선택한 취미는 없습니다")
                    // on 상태면 체크박세에 체크한  것을 출력해준다.
                    true -> {
                        // 모든 체크 박스가 체크 되어 있지 않다면
                        if(!(checkBoxHobby1.isChecked) && !(checkBoxHobby2.isChecked)
                            && !(checkBoxHobby3.isChecked)){
                            textViewResult.append("선택한 취미는 없습니다")
                        } else {
                            if (checkBoxHobby1.isChecked){
                                textViewResult.append("선택한 취미: 축구\n")
                            }
                            if(checkBoxHobby2.isChecked){
                                textViewResult.append("선택한 취미: 농구\n")
                            }
                            if(checkBoxHobby3.isChecked){
                                textViewResult.append("선택한 취미: 야구\n")
                            }
                        }
                    }
                }
            }
        }
    }
}