package com.example.roomdb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdb.Utils.Constants
import com.example.roomdb.Utils.Constants.STUDENT_TABLE


@Entity(tableName = STUDENT_TABLE)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val fname: String,
    val lname: String,
    val national_code: String,
    val grade: Grade,
)