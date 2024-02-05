package kr.co.lion.android25_activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

// Activity가 실행될 때: onCreate() -> onStart() -> onResume() -> Activity Running
// 화면 회전이 발생했을 때: onPause() -> onStop() -> onDestroy() -> Activity Dead
//                     -> onCreate() -> on Start() -> onResume() -> Activity Running
// 화면이 안보일 때 : onPause() -> onStop() -> Activity Stop
// 화면이 다시 보일 때 : onRestart() -> onStart() -> onResume() -> Activity Running
// Activity가 종료 될 때 : onPause() -> onStop() -> onDestory() -> Activity Dead

// Activity 실행시 딱 한번만 동작해야 하는 코드 : init블럭이나 생성자
// Activity 실행시 혹은 화면 회전시 동작해야 하는 코드 : onCreate
// Activity가 일시정지 혹은 정지 했을 때 동작해야 하는 코드 : onPause
// Activity가 다시 가동되거나 보여질 때 동작해야 하는 코드 : onResume
// Activity가 완전히 종료될때 동작해야 하는 코드 : onDestroy


class MainActivity : AppCompatActivity() {

    // Activity가 처음 실행 될 때
    // 화면 전환이 발생했을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("test1234", "onCreate")
    }

    // onStart
    // Activity 동작이 시작될 때
    // Stop 상태에서 다시 Start 상태가 될 때
    override fun onStart() {
        super.onStart()
        Log.d("test1234", "onStart")
    }

    // onResume
    // Activity 동작이 다시 시작될 때
    // Pause 상태에서 다시 Running 상태가 되기 전
    override fun onResume() {
        super.onResume()
        Log.d("test1234", "onResume")
    }

    // onRestart
    // Stop 상태에서 Start 상태가 되기 전에 호출된다.
    override fun onRestart() {
        super.onRestart()
        Log.d("test1234", "onRestart")
    }

    // onPause
    // Running 상태에서 일시 정지가 될 때
    override fun onPause() {
        super.onPause()
        Log.d("test1234", "onPause")
    }

    // onStop
    // Running 상태에서 정지가 될 때
    override fun onStop() {
        super.onStop()
        Log.d("test1234", "onStop")
    }

    // onDestroy
    // Activity가 종료될 때
    override fun onDestroy() {
        super.onDestroy()
        Log.d("test1234", "onDestroy")
    }
}