package kr.co.lion.ex10

import android.content.Intent
import android.inputmethodservice.Keyboard.Row
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex10.databinding.ActivityMainBinding
import kr.co.lion.ex10.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity 실행을 위한 런쳐
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        initView()
        setEvent()
    }

    // 초기 데이터 세팅
    fun initData(){
        // InputActivity 등록
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1){

        }
    }

    // View 초기 세팅
    fun initView(){
        activityMainBinding.apply {
            toolBar.apply {
                //
                inflateMenu(R.menu.main_menu)
            }
            recyclerView.apply {
                // RecyclerView에 어댑터를 설정한다.
                adapter = RecyclerViewAdapter()
                // layoutManager 설정
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    //이벤트 설정
    fun setEvent(){

    }

    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        // View Holder
        inner class ViewHolderClass(rowMainBinding: RowMainBinding): RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding

                // 항목 뷰의 가로 세로 길이 세팅
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerViewAdapter.ViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMainBinding)

            return viewHolderClass
        }

        override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolderClass, position: Int) {
            holder.rowMainBinding.textViewRowMainName.text = "홍길동"
            holder.rowMainBinding.textViewRowMainGrade.text = "000"

        }

        override fun getItemCount(): Int {
            return 20
        }

    }
}