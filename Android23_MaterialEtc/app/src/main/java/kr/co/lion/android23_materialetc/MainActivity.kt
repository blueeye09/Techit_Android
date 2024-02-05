package kr.co.lion.android23_materialetc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android23_materialetc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    // chip: 사용자 인터페이스(UI) 요소 중 하나
    // 사용자가 선택하거나 입력하는 데 사용하는 작은 사각형 모양의 요소
    // chip의 기능
    // 1. 필터링 및 태그
    // 여러 선택 사항 중에서 사용자가 원하는 것을 선택할 수 있도록 제공함.
    // EX) 상품 카테고리, 관심 주제, 색상
    // 2. 입력 및 삭제
    // 사용자가 콘텐츠를 입력하고 삭제하는 데 사용
    // 3. 단일 및 다중 선택
    // 하나 이상의 옵션을 선택하거나 해제하는 데 사용
    // 4. 액션 또는 제어
    // 작은 작업 또는 컨트롤을 나타내는 데 사용
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            chip.setOnClickListener{
                textView.text = "Chip을 눌렀습니다."
            }
        }
    }
}