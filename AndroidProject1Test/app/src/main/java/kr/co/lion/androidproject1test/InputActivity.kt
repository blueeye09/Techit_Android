package kr.co.lion.androidproject1test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import kr.co.lion.androidproject1test.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        setToolbar()
        initView()
        setEvent()
    }

    // 툴바 설정
    fun setToolbar(){
        activityInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "동물 등록"

                // Back 버튼
                setNavigationIcon(R.drawable.arrow_back_24px)
                // Back 버튼 클릭 리스너
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_input)
            }
        }
    }

    // View 들의 초기 상태를 설정한다.
    private fun initView(){
        activityInputBinding.apply {
            // 버튼 그룹에서 사자를 선택한다.
            buttonGroupInputType.check(R.id.buttonInputType1)
            // 입력요소를 관리하는 layout들 중 사자는 보이게 하고
            // 나머지들은 보이지 않게 한다.
            containerInputType1.isVisible = true
            containerInputType2.isVisible = false
            containerInputType3.isVisible = false
            // 사자의 성별은 암컷으로 선택해 놓는다.
            buttonGroupInputGender.check(R.id.buttonInputGender1)

            // 이름 입력칸에 포커스를 준다.
            // 이 화면이 뜰 때, 자동으로 키보드가 올라오도록 설정
            Util.showSoftInput(activityInputBinding.textFieldInputName, this@InputActivity)
        }
    }

    // View들의 이벤트 설정
    fun setEvent(){
        activityInputBinding.apply {
            // 동물 타입 버튼 클릭
            buttonGroupInputType.addOnButtonCheckedListener { group, checkedId, isChecked ->

                // 전부 다 안보이는 상태로 변경한다.
                containerInputType1.isVisible = false
                containerInputType2.isVisible = false
                containerInputType3.isVisible = false

                // 현재 체크되어 있는 버튼에 따라 보여지는 부분을 달리 한다.
                when(buttonGroupInputType.checkedButtonId){
                    // 사자
                    R.id.buttonInputType1 -> {
                        containerInputType1.isVisible = true
                        // 입력 요소 초기화
                        textFieldInputHairCount.setText("")
                        buttonGroupInputGender.check(R.id.buttonInputGender1)
                    }
                    // 호랑이
                    R.id.buttonInputType2 -> {
                        containerInputType2.isVisible = true
                        // 입력 요소 초기화
                        textFieldInputLineCount.setText("")
                        sliderInputWeight.value = sliderInputWeight.valueFrom
                    }
                    // 기린
                    R.id.buttonInputType3 -> {
                        containerInputType3.isVisible = true
                        // 입력 요소 초기화
                        textFieldInputNeckLength.setText("")
                        textFieldInputRunSpeed.setText("")

                    }
                }
                // 이름 입력칸에 포커스를 준다.
                Util.showSoftInput(activityInputBinding.textFieldInputName, this@InputActivity)
            }
            // 호랑이 몸무게 슬라이더
            // 두 번째: 현재 설정된 값
            // 세 번째: 사용자에 의해서 값이 변경될 경우 true가 들어온다.
            sliderInputWeight.addOnChangeListener { slider, value, fromUser ->
                // 텍스트뷰에 출력한다.
                textViewInputWeight.text = "몸무게 ${value.toInt()}kg"
            }
        }
    }
}