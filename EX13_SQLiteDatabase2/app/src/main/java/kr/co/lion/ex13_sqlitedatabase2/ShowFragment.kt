package kr.co.lion.ex13_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex13_sqlitedatabase2.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    private lateinit var fragmentShowBinding: FragmentShowBinding
    private lateinit var mainActivity: MainActivity

    // 학생 정보를 담을 객체
    private lateinit var studentModel: StudentModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setData()
        setToolbar()
        setView()

        return fragmentShowBinding.root
    }

    // 학생 한명의 데이터를 Bundle을 통해 가져온다.
    private fun setData(){
        // 학생 idx 값을 추출한다.
        val idx = arguments?.getInt("idx")!!
        // 학생 데이터를 가져온다.
        studentModel = StudentDao.selectOneStudent(mainActivity, idx)
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

        // 출력
        fragmentShowBinding.apply {
            textViewShow.apply {
                // Arguments를 통해 데이터를 추출한다.
                text = "이름: ${studentModel.name}\n"
                append("나이: ${studentModel.age}살\n")
                append("국어 점수: ${studentModel.kor}점\n")
            }
        }
    }
}