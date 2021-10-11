package com.example.projecttrue.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// Name: Johnkai Cortez
// Student id: 2012960

//Make the Database
@Database(entities = [CommentData::class], version = 1, exportSchema = false)
abstract class CommentDataBase : RoomDatabase() {

    abstract fun CommentDAO(): CommentDAO

    companion object {
        @Volatile
        private var INSTANCE: CommentDataBase? = null
        fun getInstance(context: Context): CommentDataBase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
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