package com.example.roomdb.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdb.Utils.Constants
import com.example.roomdb.Utils.Constants.DATABASE_NAME
import com.example.roomdb.model.Student
import java.time.Instant


@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class SchoolDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    companion object{
        private var INSTANCE:SchoolDatabase?=null
        fun getDatabase(context: Context):SchoolDatabase{
            if (INSTANCE==null){
                synchronized(this){
                    return Room.databaseBuilder(context.applicationContext,SchoolDatabase::class.java,
                        DATABASE_NAME).build()
                }
            }else{
                return INSTANCE as SchoolDatabase
            }
        }
    }

}
//    companion object{
//        @Volatile
//        private var INSTANCE:SchoolDatabase?=null
//
//        fun getDatabase(context: Context):SchoolDatabase{
//            val instance= INSTANCE
//            if (instance!=null){
//                return instance
//            }
//            synchronized(this){
//                val instance= Room.databaseBuilder(context.applicationContext,SchoolDatabase::class.java,DATABASE_NAME).build()
//                INSTANCE=instance
//                return instance
//            }
//        }
//    }
//
