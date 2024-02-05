package kr.co.lion.android04_linearlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// LinearLayout
// 방향성을 갖고 View 들을 배치하는 layout
// 주요 속성
// orientation: View 가 배치되는 방향을 결정한다.
//   - Vertical: 세로방향
//   - horizontal(생략): 가로 방향
// weight: 배치되는 뷰의 크기 비율

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}