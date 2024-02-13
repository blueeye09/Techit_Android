package kr.co.lion.androidproject1test

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.androidproject1test.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    // Activity 런쳐
    lateinit var modifyActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        setLauncher()
        setToolbar()
        setView()
    }

    // 런처 설정
    fun setLauncher(){
        // ModifyActivity Launcher
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher = registerForActivityResult(contract1){

        }
    }

    // 툴바 설정
    fun setToolbar(){
        activityShowBinding.apply {
            toolbarShow.apply {
                title = "동물 정보"

                // Back 버튼
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }

                // 메뉴
                inflateMenu(R.menu.menu_show)
                setOnMenuItemClickListener {
                    // 사용자가 선택한 메뉴 항목의 id로 분기한다.
                    when(it.itemId){
                        // 수정
                        R.id.menu_item_show_modify -> {
                            val modifyIntent = Intent(this@ShowActivity, ModifyActivity::class.java)
                            // 동물 순서값을 저장한다.
                            val position = intent.getIntExtra("position", 0)
                            modifyIntent.putExtra("position", position)
                            // 동물의 순서값을 intent에 담아서 ModifyActivity로 전달한다.
                            modifyActivityLauncher.launch(modifyIntent)
                        }

                        // 삭제
                        R.id.menu_item_show_delete -> {
                            // 항목 순서 값을 가져온다.
                            val position = intent.getIntExtra("position", 0)
                            // 항목번째 객체를 리스트에서 제거한다.
                            Util.animalList.removeAt(position)
                            finish()
                        }
                    }
                    true
                }
            }
        }
    }

    // 뷰 설정
    fun setView(){
        activityShowBinding.apply {
            // TextView
            textViewShowInfo.apply {
                // 항목 순서값을 가져온다.
                val position = intent.getIntExtra("position", 0)
                // 포지션번째 객체를 추출한다.
                val animal = Util.animalList[position]

                // 공통
                text = "동물 종류: ${animal.type.str}\n"
                append("이름: ${animal.name}\n")
                append("나이: ${animal.age}\n")

                // 동물 타입에 따라 분기
                when(animal.type){
                    // 사자
                    AnimalType.ANIMAL_TYPE_LION -> {
                        // lion 타입으로 형변환한다.
                        val lion = animal as Lion
                        append("털의 개수: ${lion.hairCount}개\n")
                        append("성별: ${lion.gender.str}\n")
                    }
                    // 호랑이
                    AnimalType.ANIMAL_TYPE_TIGER -> {
                        // Tiger 타입으로 형변환한다.
                        val tiger = animal as Tiger
                        append("줄무늬 개수: ${tiger.lineCount}개\n")
                        append("몸무게: ${tiger.weight}kg\n")
                    }
                    // 기린
                    AnimalType.ANIMAL_TYPE_GIRAFFE -> {
                        // Giraffe 타입으로 변환한다.
                        val giraffe = animal as Giraffe
                        append("목길이: ${giraffe.neckLength}cm\n")
                        append("달리기 속도: 시속 ${giraffe.runSpeed}km\n")
                    }
                }
            }
        }
    }

    // 뷰를 설정하는 2번째 방법 (Smart Casting)
    fun setView2(){
        activityShowBinding.apply {
            // TextView
            textViewShowInfo.apply {
                // 항목 순서값을 가져온다.
                val position = intent.getIntExtra("position", 0)
                val animal = Util.animalList[position]
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    intent.getParcelableExtra("obj", Animal::class.java)
//                } else {
//                    intent.getParcelableExtra<Animal>("obj")
//                }

                // 공통
                text = "동물 종류: ${animal.type.str}\n"
                append("이름: ${animal.name}\n")
                append("나이: ${animal.age}\n")

                // 사자
                if(animal is Lion){
                    append("털의 개수: ${animal.hairCount}개\n")
                    append("성별: ${animal.gender.str}\n")
                }
                // 호랑이
                else if(animal is Tiger){
                    append("줄무늬 개수: ${animal.lineCount}개\n")
                    append("몸무게: ${animal.weight}kg\n")
                }
                // 기린
                else if(animal is Giraffe){
                    append("목길이: ${animal.neckLength}cm\n")
                    append("달리기 속도: 시속 ${animal.runSpeed}km\n")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 다른 곳 갔다 왔을 경우 출력 내용을 다시 구성해준다.
        setView2()
    }
}