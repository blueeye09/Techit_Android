package kr.co.lion.android27_toolbar

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.android27_toolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    lateinit var activityMainBinding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            toolbar.apply {
                // 타이틀
                title = "툴바 입니다"
                // 타이틀 문자열
                setTitleTextColor(Color.WHITE)
                // res/menu/main_menu.xml 파일을 이용해 툴바의 메뉴를 생성한다.
                // id: 각 메뉴를 구분하기 위한 이름
                // title: 메뉴에 표시되는 문자열
                // showAsAction
                inflateMenu(R.menu.main_menu)

                // 메뉴를 선택하면 동작하는 리스너
                // 매개변수에는 사용자가 선택한 메뉴 항목의 객체가 전달된다.
                setOnMenuItemClickListener {
                    // 선택한 메뉴의 id로 분기한다.
                    when(it.itemId){
                        R.id.menuItem1 -> textView.text = "메뉴1을 선택했습니다"
                        R.id.menuItem2 -> textView.text = "메뉴2를 선택했습니다"
                        R.id.menuItem31 -> textView.text = "메뉴 3-1을 선택했습니다"
                        R.id.menuItem32 -> textView.text = "메뉴 3-2를 선택했습니다"
                    }

                    true
                }
            }

            // 버튼을 누르면 SecondActivity Intent를 넘겨준다.
            buttonStartSecond.setOnClickListener {
                val newIntent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(newIntent)
            }
        }
    }
}