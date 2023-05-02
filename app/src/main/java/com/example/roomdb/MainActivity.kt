package com.example.roomdb

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.roomdb.Db.SchoolDatabase
import com.example.roomdb.Utils.Constants
import com.example.roomdb.Utils.Constants.DATABASE_NAME
import com.example.roomdb.ViewModel.StudentViewModel
import com.example.roomdb.model.Grade
import com.example.roomdb.model.Student
import com.example.roomdb.ui.theme.RoomDbTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RoomDbTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Scaffold(
                        drawerContent = {},

                        topBar = {
                                 TopAppBar(modifier = Modifier
                                     .fillMaxWidth()
                                     .background(Color.White), backgroundColor = Color.White, elevation = 8.dp, contentColor = Color.Black) {
                                     Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                         IconButton(onClick = {
                                             GlobalScope.launch {
                                                 val viewmodel by viewModels<StudentViewModel>()
                                                 viewmodel.addStudent(Student((0 until 100).random(),"test","test","test",Grade.ONE))
                                                 viewmodel.allStudetns.collectLatest {studetns->
                                                     for (student in studetns){
                                                         Log.e("3636",student.fname.toString())
                                                     }
                                                 }

                                            }

                                         }) {
                                             Icon(Icons.Filled.Add, contentDescription = "")
                                         }

                                     }

                                 }
                        },
                        content = {
                            ObserveStudent()

                        },
                    )
                }
            }
        }
    }
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    fun ObserveStudent(){
        val viewmodel by viewModels<StudentViewModel>()
//        viewmodel.addStudent(Student(1,"test","test","test",Grade.ONE))
//        viewmodel.addStudent(Student(2,"test2","test2","test2",Grade.ONE))
        var studentList by remember {
            mutableStateOf(emptyList<Student>())
        }
        LaunchedEffect(key1 = true) {
            viewmodel.allStudetns.collectLatest {studetns->
                studentList=studetns
            }
        }
        if (studentList.size==0){
            
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "There is nothing to show ...")
                
            }
        }else{
            StudentView(studentList = studentList)
        }
    }



    @Composable
    fun StudentView(studentList:List<Student>){
        LazyColumn() {
            items(studentList.size) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(80.dp),
                    backgroundColor = Color.White,
                    elevation = 4.dp,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {

                            Text(text = studentList[it].id.toString() +" "+studentList[it].fname.toString() +" "+ studentList[it].lname.toString(), modifier = Modifier.padding(10.dp))
                            Text(text = studentList[it].national_code.toString(), modifier = Modifier.padding(10.dp))


                        }
                        IconButton(onClick = {DeleteStudent(student = studentList[it])}) {
                            Icon(Icons.Filled.Clear, contentDescription = "", tint = Color.Black)
                        }

                    }


                }
            }

        }

    }

    fun DeleteStudent(student:Student){
        val viewmodel by viewModels<StudentViewModel>()
        viewmodel.deleteStudet(student)

    }


}




@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomDbTheme {
        Greeting("Android")
    }
}