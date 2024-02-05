package kr.co.lion.ex08

import android.inputmethodservice.Keyboard.Row
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.ex08.databinding.ActivityMainBinding
import kr.co.lion.ex08.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    // data를 보유한 간단한 class
    data class User(val id: String, val name: String)

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var textId: String
    lateinit var textName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // 어댑터 객체 생성
            val recyclerViewAdapter = RecyclerViewAdapter()
            // 어댑터 적용
            recyclerView.adapter = recyclerViewAdapter
            // RecyclerView의 항목을 보여줄 방식을 설정한다.
            // 위에서 아래 방향
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            // RecyclerView Decoration
            // 각 항목을 구분하기 위한 구분선
            val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(deco)

            buttonConfirm.apply {
                setOnClickListener {
                    textId = textInputId.text.toString()
                    textName = textInputName.text.toString()
                    recyclerViewAdapter.addUser(textId, textName)
                }
            }
        }
    }

    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        // 데이터를 담을 리스트
        private val userList = mutableListOf<User>()

        // View Holder
        inner class ViewHolderClass(val rowBinding: RowBinding): RecyclerView.ViewHolder(rowBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val binding = RowBinding.inflate(layoutInflater, parent, false)
            return ViewHolderClass(binding)
        }

        override fun getItemCount(): Int {
            return userList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            val user = userList[position]
            holder.rowBinding.apply {
                textViewId.text = user.id
                textViewName.text = user.name
            }
        }

        // 외부에서 데이터를 추가하는 메서드
        fun addUser(id: String, name: String) {
            userList.add(User(id, name))
            notifyDataSetChanged()
        }
    }
}