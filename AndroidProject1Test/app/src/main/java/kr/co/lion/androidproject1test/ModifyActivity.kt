package kr.co.lion.androidproject1test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kr.co.lion.androidproject1test.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {

    lateinit var activityModifyBinding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityModifyBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(activityModifyBinding.root)

        setToolbar()
        initView()
    }

    // 툴바 설정
    fun setToolbar(){
        activityModifyBinding.apply {
            toolbarModify.apply {
                title = "동물 정보 수정"

                // Back 버튼 설정
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify)
                // 완료 버튼 클릭
                setOnMenuItemClickListener {
                    modifyData()
                    finish()
                    true
                }
            }
        }
    }

    // 뷰 설정
    fun initView(){
        activityModifyBinding.apply {
            // 각 동물별 입력 요소를 숨긴다.
            containerModifyType1.isVisible = false
            containerModifyType2.isVisible = false
            containerModifyType3.isVisible = false

            // 순서 값을 추출한다.
            val position = intent.getIntExtra("position", 0)
            // position번째 객체 추출
            val animal = Util.animalList[position]

            textFieldModifyName.setText(animal.name)
            textFieldModifyAge.setText("${animal.age}")

            // 동물의 타입으로 분기한다.
            when(animal.type){
                // 사자
                AnimalType.ANIMAL_TYPE_LION -> {
                    // 입력 요소들을 보이게 한다.
                    containerModifyType1.isVisible = true
                    // 형변환
                    val lion = animal as Lion
                    textFieldModifyHairCount.setText("${lion.hairCount}")
                    when (lion.gender){
                        LION_GENDER.LION_GENDER1 -> {
                            buttonGroupModifyGender.check(R.id.buttonModifyGender1)
                        }
                        LION_GENDER.LION_GENDER2 -> {
                            buttonGroupModifyGender.check(R.id.buttonModifyGender2)
                        }
                    }
                }
                // 호랑이
                AnimalType.ANIMAL_TYPE_TIGER -> {
                    // 입력 요소들을 보이게 한다.
                    containerModifyType2.isVisible = true
                    // 형변환
                    var tiger = animal as Tiger
                    textFieldModifyLineCount.setText("${tiger.lineCount}")
                    sliderModifyWeight.value = tiger.weight.toFloat()
                }
                // 기린
                AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                    // 입력 요소들을 보이게 한다.
                    containerModifyType3.isVisible = true
                    // 형변환
                    var giraffe = animal as Giraffe
                    textFieldModifyNeckLength.setText("${giraffe.neckLength}")
                    textFieldModifyRunSpeed.setText("${giraffe.runSpeed}")
                }
            }
        }
    }

    // 수정 처리
    fun modifyData(){
        // 위치값을 가져온다
        val position = intent.getIntExtra("position", 0)
        // position번째 객체를 가져온다.
        val animal = Util.animalList[position]

        activityModifyBinding.apply {
            // 공통
            animal.name = textFieldModifyName.text.toString()
            animal.age = textFieldModifyAge.text.toString().toInt()
            // 클래스 타입 별로 분기한다.
            // 사자
            if (animal is Lion){
                animal.hairCount = textFieldModifyHairCount.text.toString().toInt()
                animal.gender = when(buttonGroupModifyGender.checkedButtonId){
                    R.id.buttonModifyGender1 -> LION_GENDER.LION_GENDER1
                    R.id.buttonModifyGender2 -> LION_GENDER.LION_GENDER2
                    else -> LION_GENDER.LION_GENDER1
                }
            }
            // 사자
            else if (animal is Tiger){
                animal.lineCount = textFieldModifyLineCount.text.toString().toInt()
                animal.weight = sliderModifyWeight.value.toInt()
            }
            // 기린
            else if (animal is Giraffe){
                animal.neckLength = textFieldModifyNeckLength.text.toString().toInt()
                animal.runSpeed = textFieldModifyRunSpeed.text.toString().toInt()
            }
        }
    }
}