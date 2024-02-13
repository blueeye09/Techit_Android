package kr.co.lion.ex11_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex11_fragment.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    private lateinit var fragmentShowBinding: FragmentShowBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setToolbar()
        setView()

        return fragmentShowBinding.root
    }

    private fun setToolbar(){
        fragmentShowBinding.apply {
            toolbarShow.apply {
                // 제목
                title = "학생 정보"
                setNavigationIcon(R.drawable.arrow_back_24px)
                // 뒤로가기 버튼을 눌렀을 때 해당 fragment 삭제
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_FRAGMENT)
                }
            }
        }
    }

    private fun setView(){
        fragmentShowBinding.apply {

            textViewShow.apply {
                // Arguments를 통해 데이터를 추출한다.
                text = "이름: ${arguments?.getString("name")}\n"
                append("나이: ${arguments?.getInt("age")}\n")
                append("국어 점수: ${arguments?.getInt("kor")}\n")
                append("영어 점수: ${arguments?.getInt("eng")}\n")
                append("수학 점수: ${arguments?.getInt("math")}\n")
            }
        }
    }
}