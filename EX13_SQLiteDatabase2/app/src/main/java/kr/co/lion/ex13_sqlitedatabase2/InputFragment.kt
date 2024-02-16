package kr.co.lion.ex13_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.ex13_sqlitedatabase2.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var fragmentInputBinding: FragmentInputBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentInputBinding.root
    }

    // 툴바 설정
    private fun settingToolbar(){
        fragmentInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "정보 입력"
                // 메뉴
                inflateMenu(R.menu.input_menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuItemInputDone -> {
                            // 데이터를 저장한다.
                            saveStudentInfo()
                            // 이전 화면으로 돌아간다.
                            mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                        }
                    }

                    true
                }
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                }
            }
        }
    }

    // 입력된 정볼르 저장한다.
    private fun saveStudentInfo(){
        fragmentInputBinding.apply {
            // 입력한 정보를 가지고 온다.
            val name = textFieldInputName.text.toString()
            val age = textFieldInputAge.text.toString().toInt()
            val kor = textFieldInputKor.text.toString().toInt()

            // 객체에 담는다.
            val studentModel = StudentModel(0, name, age, kor)

            StudentDao.insertStudent(mainActivity, studentModel)
        }
    }
}