package kr.co.lion.ex13_sqlitedatabase2

import android.content.Context

class StudentDao {

    companion object{
        // CRUD -> Create, Read, Update, Delete
        // Create -> Insert
        // Read -> Select
        // Update -> Update
        // Delete -> Delete


        // select one
        fun selectOneStudent(context: Context, idx:Int): StudentModel {
            // 쿼리문
            val sql = """select idx, name, age, kor
                | from StudentTable
                | where idx = ?
            """.trimMargin()

            // ?에 Binding될 값
            val args = arrayOf("$idx")
            // query 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 데이터를 담는다.
            cursor.moveToNext()

            // 각 컬럼별 순서 값을 가져온다.
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("name")
            val idx3 = cursor.getColumnIndex("age")
            val idx4 = cursor.getColumnIndex("kor")

            // 데이터를 가져온다.
            val index = cursor.getInt(idx1)
            val name = cursor.getString(idx2)
            val age = cursor.getInt(idx3)
            val kor = cursor.getInt(idx4)

            // 객체에 담는다.
            val studentModel = StudentModel(index, name, age, kor)

            cursor.close()
            dbHelper.close()

            return studentModel
        }

        // select all
        fun selectAllStudent(context:Context): MutableList<StudentModel>{
            // 쿼리문
            val sql = """select idx, name, age, kor
                    | from StudentTable
                    | order by idx desc
                """.trimMargin()

            // 객체를 담을 리스트
            val studentList = mutableListOf<StudentModel>()

            // query 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            // 데이터를 담는다.
            while(cursor.moveToNext()) {

                // 각 컬럼별 순서 값을 가져온다.
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("name")
                val idx3 = cursor.getColumnIndex("age")
                val idx4 = cursor.getColumnIndex("kor")

                // 데이터를 가져온다.
                val idx = cursor.getInt(idx1)
                val name = cursor.getString(idx2)
                val age = cursor.getInt(idx3)
                val kor = cursor.getInt(idx4)

                // 객체에 담는다.
                val studentModel = StudentModel(idx, name, age, kor)
                studentList.add(studentModel)
            }
            cursor.close()
            dbHelper.close()
            return studentList
        }

        // insert
        fun insertStudent(context:Context, studentModel: StudentModel){
            // 쿼리문
            val sql = """insert into StudentTable
                | (name, age, kor)
                | values (?, ?, ?)
            """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(studentModel.name, studentModel.age, studentModel.kor)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // update
        fun updateStudent(context:Context, studentModel: StudentModel){
            // 쿼리문
            val sql = """update StudentTable
                | set name = ?, age = ?, kor = ?
                | where idx = ?
            """.trimMargin()
            // ?에 바인딩 될 값
            val args = arrayOf(studentModel.name, studentModel.age, studentModel.kor, studentModel.idx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // delete
        fun deleteStudent(context:Context, idx:Int){
            // 쿼리문
            val sql = """delete from StudentTable
                | where idx = ?
            """.trimMargin()

            // ?에 바인딩 될 값
            val args = arrayOf(idx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }

}