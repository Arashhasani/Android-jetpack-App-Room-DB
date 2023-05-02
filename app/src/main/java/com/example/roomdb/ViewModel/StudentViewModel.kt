package com.example.roomdb.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.*
import androidx.room.Room
import com.example.roomdb.Db.SchoolDatabase
import com.example.roomdb.Utils.Constants
import com.example.roomdb.model.Grade
import com.example.roomdb.model.Student
import com.example.roomdb.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StudentViewModel(application: Application):AndroidViewModel(application) {
    val allStudetns:Flow<List<Student>>
    private val repostitory:StudentRepository
    init {

        val studentDao=SchoolDatabase.getDatabase(application).studentDao()
        repostitory= StudentRepository(studentDao)
        allStudetns=repostitory.allstudents
    }

     fun addStudent(student:Student){
         viewModelScope.launch (Dispatchers.IO){
             repostitory.addStudent(student = student)
         }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repostitory.deleteallStudet()
        }
    }
    fun deleteStudet(student:Student){
        viewModelScope.launch (Dispatchers.IO){
            repostitory.deleteStudet(student)
        }
    }

}