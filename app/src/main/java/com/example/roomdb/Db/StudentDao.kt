package com.example.roomdb.Db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.roomdb.Utils.Constants
import com.example.roomdb.Utils.Constants.STUDENT_TABLE
import com.example.roomdb.model.Student
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentDao {


    @Query("SELECT * FROM $STUDENT_TABLE")
      fun getAllStudents(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
       fun inserStudent(student: Student)


    @Delete
    fun deleteStudent(student: Student)

    @Query("DELETE FROM $STUDENT_TABLE")
    fun deleteEverythings()

}