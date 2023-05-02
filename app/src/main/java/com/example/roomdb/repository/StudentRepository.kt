package com.example.roomdb.repository

import com.example.roomdb.Db.StudentDao
import com.example.roomdb.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao:StudentDao) {
    val allstudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun addStudent(student:Student){
        studentDao.inserStudent(student = student)
    }
    suspend fun deleteStudet(student:Student){
        studentDao.deleteStudent(student = student)
    }

    suspend fun deleteallStudet(){
        studentDao.deleteEverythings()
    }


}