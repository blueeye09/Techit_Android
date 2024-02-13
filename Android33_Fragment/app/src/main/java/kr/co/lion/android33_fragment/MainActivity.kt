package kr.co.lion.android33_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.android33_fragment.databinding.ActivityMainBinding

// Activity의 역할 :
// 1. Fragment 들을 관리
// 2. 각 Fragment 들이 공통적으로 사용하는 데이터들을 관리
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    // 이전 Fragment
    var oldFragment:Fragment? = null
    // 새로 나타날 Fragment
    var newFragment:Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // MainFragment가 보여지도록 한다.
        replaceFragment(FragmentName.MAIN_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    // name: Fragment 이름
    // addToBackStack: BackStack에 포함 시킬 것인지
    // isAnimate: Animation을 보여줄 것인지
    // data: 새로운 Fragment에 전달 할 값이 담긴 Bundle 객체
    fun replaceFragment(name:FragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){

        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        when (name){
            FragmentName.MAIN_FRAGMENT -> {
                newFragment = MainFragment()
            }
            FragmentName.INPUT_FRAGMENT -> {
                newFragment = InputFragment()
            }
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments Property에 넣어준다.
        if (data != null) {
            newFragment?.arguments = data
        }

        if(newFragment != null){

            // 애니메이션 설정
            if(isAnimate){
                // oldFragment -> newFragment
                // oldFragment: exit
                // newFragment: enter

                // newFragment -> oldFragment
                // oldFragment: reenter
                // newFragment: return

                // MaterialSharedAxis: 좌우, 위아래, 공중 바닥 사이로 이동하는 애니메이션 효과
                // X - 좌우
                // Y - 상하
                // Z - 공중, 바닥
                // 두 번째 매개변수: 새로운 화면이 나타나는 것인지 여부를 설정
                // true: 새로운 화면이 나타나는 애니메이션이 동작
                // false: 이전으로 되돌아가는 animation 동작

                if (oldFragment != null){
                    // old에서 new가 새롭게 보여질 때 old의 애니메이션
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    // new에서 old로 되돌아갈 때 old의 애니메이션
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                // old에서 new가 새롭게 보여질 때 new의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // new에서 old로 되돌아갈 때 new의 애니메이션
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }

            // Fragment를 교체한다. (이전 Fragment가 없으면 새롭게 추가하는 역할을 수행한다)
            // 첫 번째 매개 변수: Fragment를 배치할 FragmentContainerview의 ID
            // 두 번째 매개 변수: 보여주고자 하는 Fragment 객체
            // 즉 2번째 매개변수를 첫번째 매개변수에 배치하는 메서드
            fragmentTransaction.replace(R.id.mainContainer, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if (addToBackStack){
                // BackStack 포함 시킬 때 이름을 지정해주면 원하는 Fragment 를 BackStack에서 제거할 수 있다.
                fragmentTransaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name:FragmentName){
        SystemClock.sleep(200)

        // 지정한 이름의 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

// Fragment들의 이름
enum class FragmentName(var str:String){
    MAIN_FRAGMENT("mainFragment"),
    INPUT_FRAGMENT("inputFragment")
}

// 학생 정보를 담을 클래스
data class StudentInfo(var name:String, var age:Int, var kor:Int, var math:Int, var eng:Int)