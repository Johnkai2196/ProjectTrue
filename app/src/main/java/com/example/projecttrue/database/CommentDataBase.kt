package com.example.projecttrue.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Name: Johnkai Cortez
// Student id: 2012960
// 11.10.2021

//Make the Database
@Database(entities = [CommentData::class], version = 1, exportSchema = false)
abstract class CommentDataBase : RoomDatabase() {
    //connect database to DAO
    abstract fun CommentDAO(): CommentDAO

    //companion object for adding funtions to the database class
    companion object {
        @Volatile
        //variable INSTANCE keeps a reference to database returned via getInstance
        private var INSTANCE: CommentDataBase? = null

        //Funtion for returning the database if it already has been retrieved
        //and making one new database if the instance is null
        fun getInstance(context: Context): CommentDataBase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            //initializin the database once using synchronized method,
            //so only one thread can ask for the database
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CommentDataBase::class.java, "database_Comment"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}