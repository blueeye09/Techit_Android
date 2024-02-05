package kr.co.lion.android06_constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// ConstraintLayout
// constraintLayout에 배치되는 뷰들은 부모 혹은 다른 뷰들과의 관계를 나타내는
// 제약 조건을 설정하여 뷰들을 배치한다.

// 제약 조건은 선으로 표시된다.
// 직선: 절대적 길이, 물리적인 액정 사이즈에 관계없이 고정된 길이, 길이 설정
// 무조건 이 길이만큼은 띄우겠다는 길이
// 물리적 액정이 작은 기기에서는 문제가 될 수 있음.
// 지그재그 선: 상대적 길이, 물리적인 액정 사이즈에 따라 변화하는 가변 길이, 비율 설정

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}