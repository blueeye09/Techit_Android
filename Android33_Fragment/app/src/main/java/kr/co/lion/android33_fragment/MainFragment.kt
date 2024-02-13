package kr.co.lion.android33_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android33_fragment.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    // Fragment가 눈에 보여질 때 호출되는 메서드
    // 반환하는 View를 화면에 보여준다.

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        // fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

        // Activity의 주소값을 담아준다.
        mainActivity = activity as MainActivity

        viewSetting()

        return fragmentMainBinding.root
    }

    // View 설정
    fun viewSetting(){
        fragmentMainBinding.apply {
            // 버튼
            buttonShowInput.setOnClickListener {
                // 버튼을 누르면 mainActivity에서 Fragment 교체 Method 호출
                // InputFragment로 교체한다.

                // 데이터를 담을 Bundle 객체를 생성한다.
                val bundle = Bundle()
                // 데이터를 담는다.
                bundle.putInt("data1", 100)
                bundle.putDouble("data2", 11.11)
                bundle.putString("data3", "String1")
                mainActivity.replaceFragment(FragmentName.INPUT_FRAGMENT, addToBackStack = true,
                    isAnimate = true, data = bundle)
            }
        }
    }
}