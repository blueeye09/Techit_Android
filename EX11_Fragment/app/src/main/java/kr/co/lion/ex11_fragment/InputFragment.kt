package kr.co.lion.ex11_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex11_fragment.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var fragmentInputBinding: FragmentInputBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setToolbar()

        return fragmentInputBinding.root
    }

    // 툴바 설정
    private fun setToolbar(){
        fragmentInputBinding.apply {
            toolbarInput.apply {
                // 제목 설정
                title = "학생 정보 입력"

                // 메뉴
                inflateMenu(R.menu.input_menu)

                setNavigationIcon(R.drawable.arrow_back_24px)
                // 뒤로가기 버튼을 눌렀을 때
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // Done 메뉴를 눌렀을 때
                        R.id.menuItemInputDone -> {
                            addStudentData()
                        }
                    }
                    true
                }
            }
        }
    }

    // 저장 처리
    private fun addStudentData(){
        fragmentInputBinding.apply {
            val name = textFieldInputName.text.toString()
            val age = textFieldInputAge.text.toString().toInt()
            val kor = textFieldInputKor.text.toString().toInt()
            val eng = textFieldInputEng.text.toString().toInt()
            val math = textFieldInputMath.text.toString().toInt()

            val student = StudentInfo(name, age, kor, eng, math)
            // studentList에 방금 만든 student 객체 추가
            mainActivity.studentList.add(student)
        }

        // 객체 전달 후엔 backStack에서 제거
        mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
    }
}