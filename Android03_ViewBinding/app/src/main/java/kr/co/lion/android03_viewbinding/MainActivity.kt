package kr.co.lion.android03_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import kr.co.lion.android03_viewbinding.databinding.ActivityMainBinding

// ViewBinding
// 코드를 통해 배치된 View들을 사용하기 위해서는 View 객체의 주소값을 가져와야 한다.
// findViewById 메서드를 이용해 원하는 View 객체의 주소값을 가져올 수 잇다.
// 만약 ViewBinding을 설정하면 id가 설정되어 있는 View 객체의 주소값이
// 미리 property에 담겨져 있기 때문에 이것을 사용만 하면 된다.

// 세팅 방법
// 1. Module 수준의 build.gradle.kts 파일을 열어준다.
// 2. build.gradle.kts 파일에 다음과 같이 작성한 후 sync now 를 눌러준다.
//     buildFeatures{
//        viewBinding = true
//    }
// 3. ViewBinding 객체를 얻어 온다.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding 사용 하지 않은 것
//        setContentView(R.layout.activity_main)
//
//        // View의 주소값을 가져온다.
//        val v1 = findViewById<TextView>(R.id.textView)
//        val v2 = findViewById<Button>(R.id.button)
//
//        // button에 button을 눌렀을 때 동작할 리스너를 설정 해준다.
//        v2.setOnClickListener(object : OnClickListener {
//            // 버튼을 누르면 호출되는 메서드
//            override fun onClick(v: View?) {
//                // TextView에 문자열을 설정한다.
//                v1.text = "버튼을 눌렀습니다."
//            }
//        })

        // ViewBinding 사용

        // ViewBinding 객체를 얻어 온다.
        // activity_main.xml을 관리하는 ViewBinding을 통해 UI 요소들의 객체를 생성한다.
        // ViewBinding 클래스의 이름은 layout 폴더에 있는 xml 파일의 이름을 기초로 결정된다.
        // activity_main.xml -> ActivityMainBinding
        // activity_lion.xml -> ActivityLionBinding
        // layoutInflater: xml 파일을 통해서 View 객체를 생성할 수 있는 도구
        // xml 파일에 배치한 모든 View 들의 객체를 생성하고 객체의 주소값을 담을 프로퍼티를
        // 만들어 객체의 주소값을 담아 준다. 이러한 property를 ViewBinding 객체가 가지고 있다.
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        // ViewBinding이 관리하는 view들 중 최상위 view를 설정하여 화면에 보여준다.
        // ViewBinding이 가지고 있는 root property는 가장 최상위에 있는 View를 지칭한다.
        setContentView(activityMainBinding.root)

        // Viewbinding 객체에 id가 설정되어 있는 View 객체의 주소값이 담겨져 있는
        // property가 있기 때문에 property를 이용해 View 객체에 접근한다.
        activityMainBinding.button.setOnClickListener(object : OnClickListener {
            // 버튼을 누르면 호출되는 메서드
            override fun onClick(v: View?) {
                // TextView에 문자열을 설정한다.
                activityMainBinding.textView.text = "버튼을 눌렀습니다."
            }
        })
    }
}