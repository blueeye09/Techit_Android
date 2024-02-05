package kr.co.lion.android20_recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android20_recycleview.databinding.ActivityMainBinding
import kr.co.lion.android20_recycleview.databinding.RowBinding

// AdapterView: 무한개의 항목을 보여주는 목적으로 사용하는 뷰
// Adapter를 사용하기 때문에 AdapterView라고 부른다.

// Adapter: View를 구성하기 위해 필요한 정보를 가지고있는 요소

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 이미지의 id
    val imageRes = arrayOf(
        R.drawable.imgflag1, R.drawable.imgflag2, R.drawable.imgflag3,
        R.drawable.imgflag4, R.drawable.imgflag5, R.drawable.imgflag6,
        R.drawable.imgflag7, R.drawable.imgflag8
    )

    // 문자열1
    val textData1 = arrayOf(
        "토고", "프랑스", "스위스", "스페인", "일본", "독일", "브라질", "대한민국"
    )

    // 문자열2
    val textData2 = arrayOf(
        "탈락", "진출", "탈락", "진출", "탈락", "진출", "진출", "진출"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            // 어댑터 객체 생성
            val recyclerViewAdapter = RecyclerViewAdapter()
            // 어댑터를 적용해준다.
            recyclerView.adapter = recyclerViewAdapter
            // RecyclerView의 항목을 보여줄 방식을 설정한다.
            // 위에서 아래 방향
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            // RecyclerView Decoration
            // 각 항목을 구분하기 위한 구분선
            val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
            // 구분선 좌측 여백
            // deco.dividerInsetStart = 50
            // 구분선 우측 여백
            // deco.dividerInsetEnd = 50

            recyclerView.addItemDecoration(deco)
        }
    }

    // Adapter
    // RecyclerView 구성을 위해 필요한 코드들이 작성되어 있다.
    // 1. 클래스를 작성한다

//    inner class RecyclerViewAdapter{
//
//    }

    // 2. ViewHolder 클래스를 작성해준다.
    // ViewHolder : View의 id를 가지고 있는 요소
//    inner class RecyclerViewAdapter{
//
//        // ViewHolder
//        inner class ViewHolderClass(rowBinding:RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
//            // 매개변수로 들어오는 바인딩객체를 담을 프로퍼티
//            var rowBinding:RowBinding
//
//            init{
//                this.rowBinding = rowBinding
//            }
//        }
//    }

    // 3. adapter 클래스를 Adapter를 상속받게 구현해준다.
    // 필요한 메서드들을 구현해준다.
    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){

        // ViewHolder
        inner class ViewHolderClass(rowBinding: RowBinding): RecyclerView.ViewHolder(rowBinding.root){
            // 매개변수로 들어오는 바인딩 객체를 담을 프로퍼티
            var rowBinding: RowBinding

            init {
                this.rowBinding = rowBinding

                // 현재 항목을 누르면 반응하는 리스너
                // adapterPosition 프로퍼티: 항목의 순서값
                // 사용자가 터치한 항목이 몇 번째 항목인가로 사용한다.
                this.rowBinding.root.setOnClickListener {
                    activityMainBinding.textView.text = "선택한 항목: ${textData1[adapterPosition]}"
                }
                // View의 가로길이는 최대 크기로 맞춰준다.
                this.rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, // 가로 길이
                    ViewGroup.LayoutParams.WRAP_CONTENT // 세로 길이
                    )
            }
        }

        // ViewHolder 객체를 생성하여 반환한다.
        // 새롭게 항목이 보여질 때 재사용 가능한 항목이 없다면 이 메서드를 호출한다.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            // View Binding
            val rowBinding = RowBinding.inflate(layoutInflater)
            // View Holder
            val viewHolderClass = ViewHolderClass(rowBinding)
            // 반환한다.
            return viewHolderClass
        }

        // RecyclerView를 통해 보여줄 항목 전체의 개수를 반환한다.
        override fun getItemCount(): Int {
            return imageRes.size
        }

        // 항목의 View에 보여주고자 하는 데이터를 설정한다.
        // 첫 번째 매개변수: ViewHolder 객체. 재사용 가능한 것이 없다면 onCreateViewHolder 메서드를
        // 호출하고 반환하는 ViewHolder 객체가 들어오고 재사용 가능한 것이 있다면
        // 재사용 가능한 ViewHolder 객체가 들어온다.
        // 두 번째 매개변수: 구성하고자 하는 항목의 순서 값(0부터 1씩 증가)
        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBinding.imageViewRow.setImageResource(imageRes[position])
            holder.rowBinding.textViewRow1.text = textData1[position]
            holder.rowBinding.textViewRow2.text = textData2[position]
        }
    }
}