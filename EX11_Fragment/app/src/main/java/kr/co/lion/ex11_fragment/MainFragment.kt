package kr.co.lion.ex11_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex11_fragment.databinding.FragmentMainBinding
import kr.co.lion.ex11_fragment.databinding.MainRowBinding

class MainFragment : Fragment() {

    private lateinit var fragmentMainBinding: FragmentMainBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        setToolbar()
        setView()

        return fragmentMainBinding.root
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
                            mainActivity.replaceFragment(FragmentName.INPUT_FRAGMENT,
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
            return mainActivity.studentList.size
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            // position번째 학생 객체를 가져온다.
            val student = mainActivity.studentList[position]
            // 해당 position의 학생 이름을 보여준다.
            holder.mainRowBinding.textViewMainRowName.text = student.name

            // 해당 position을 눌렀을 때
            holder.mainRowBinding.root.setOnClickListener{
                // Bundle에 해당 객체의 정보를 담아준다.
                val bundle = Bundle()
                // 데이터를 담는다.
                bundle.putString("name", student.name)
                bundle.putInt("age", student.age)
                bundle.putInt("kor", student.kor)
                bundle.putInt("eng", student.eng)
                bundle.putInt("math", student.math)

                // Bundle data를 포함해서 Fragment를 교체한다
                // 이 때, Back Stack에도 추가해준다.
                mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, addToBackStack = true,
                    isAnimate = true, data = bundle)
            }
        }
    }
}