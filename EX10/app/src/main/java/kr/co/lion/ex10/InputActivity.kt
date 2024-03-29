package kr.co.lion.ex10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.ex10.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        initData()
        initView()
        setEvent()
    }

    // 초기 데이터 셋팅
    fun initData(){

    }
    // View 초기 셋팅
    fun initView(){
        activityInputBinding.apply {
            toolBarInput.apply {
                // 툴바에 input_menu를 붙여줌.
                inflateMenu(R.menu.input_menu)

                // 좌측 상단에 홈 버튼 아이콘을 설정해줌
                setNavigationIcon(R.drawable.arrow_back_24px)

                // 좌측 상단의 홈 버튼 아이콘을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    // 현재 액티비티를 종료시킨다.
                    finish()
                }
            }
        }
    }
    // 이벤트 설정
    fun setEvent() {
        activityInputBinding.apply {
            toolBarInput.apply {
                setOnMenuItemClickListener{
                    // 입력한 정보를 객체에 담는다.
                    val name = inputName.text.toString()
                    val grade = inputGrade.text.toString().toInt()
                    val kor = inputKor.text.toString().toInt()
                    val eng = inputEng.text.toString().toInt()
                    val math = inputMath.text.toString().toInt()

                    val info1 = InfoClass(name, grade, kor, eng, math)

                    // 데이터를 담을 Intent를 생성한다.
                    val resultIntent = Intent()

                    when(it.itemId){
                        R.id.menuItemSubmit
                        -> {
                            // 객체를 Intent에 저장할 때 writeToParcel 메서드가 호출된다.
                            resultIntent.putExtra("obj", info1)

                            // 결과를 세팅한다
                            setResult(RESULT_OK, resultIntent)

                            // 현재 Activity를 종료한다.
                            finish()
                        }
                    }
                    true
                }
            }
        }
    }
}