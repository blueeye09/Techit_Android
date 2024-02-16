package kr.co.lion.ex13_sqlitedatabase2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex13_sqlitedatabase2.databinding.FragmentMainBinding
import kr.co.lion.ex13_sqlitedatabase2.databinding.MainRowBinding

class MainFragment : Fragment() {

    private lateinit var fragmentMainBinding: FragmentMainBinding
    private lateinit var mainActivity: MainActivity

    // 학생 객체를 담을 리스트
    lateinit var studentList: MutableList<StudentModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setData()
        setToolbar()
        setView()

        return fragmentMainBinding.root
    }

    // 데이터를 설정
    private fun setData(){
        // 학생들의 정보를 가져온다.
        studentList = StudentDao.selectAllStudent(mainActivity)
    }

    // 툴바 설정
    private fun setToolbar(){
        fragmentMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "학생 관리"
                // menu
                inflateMenu(R.menu.main_menu)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // Add 버튼을 누르면
                        R.id.menuItemMainAdd -> {
                            // mainActivity에서 Fragment 교체 Method 호출
                            // InputFragment로 교체한다
                            mainActivity.replaceFragment(
                                FragmentName.INPUT_FRAGMENT,
                                addToBackStack = true,
                                isAnimate = true, data = null)
                        }
                    }
                    true
                }
            }
        }
    }

    // View 설정 (Recycler View)
    private fun setView(){
        fragmentMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                // 어댑터
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(mainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // RecyclerView의 어댑터 클래스
    inner class RecyclerViewMainAdapter: RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>(){
        // ViewHolder
        inner class ViewHolderMain(mainRowBinding: MainRowBinding): RecyclerView.ViewHolder(mainRowBinding.root){
            val mainRowBinding: MainRowBinding

            init {
                this.mainRowBinding = mainRowBinding
                this.mainRowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val mainRowBinding = MainRowBinding.inflate(layoutInflater)

            return ViewHolderMain(mainRowBinding)
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            // position번째 학생 객체를 가져온다.
            val student = studentList[position]

            holder.mainRowBinding.textViewMainRowName.text = student.name

            // 해당 position을 눌렀을 때
            holder.mainRowBinding.root.setOnClickListener{
                // Bundle에 해당 객체의 정보를 담아준다.
                val showBundle = Bundle()

                // 학생 번호를 번들에 담는다.
                showBundle.putInt("idx", student.idx)
                mainActivity.replaceFragment(
                    FragmentName.SHOW_FRAGMENT, addToBackStack = true,
                    isAnimate = true, data = showBundle)
            }
        }
    }
}