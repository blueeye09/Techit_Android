package kr.co.lion.ex12_sqlitedatabase1


// 학생번호, 이름, 학년, 국어 점수, 영어 점수, 수학 점수
data class StudentModel(var idx:Int, var name:String, var grade:Int,
    var kor:Int, var eng:Int, var math:Int)