package com.example.projecttrue.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Name: Johnkai Cortez
// Student id: 2012960
// 5.10.2021

//Make the Database
@Database(entities = [ParlamentMemberData::class], version = 1, exportSchema = false)
abstract class ParlamentMemberDataBase : RoomDatabase() {
    //connect database to DAO
    abstract fun parlamentDAO(): ParlamentDAO

    //companion object for adding funtions to the database class
    companion object {
        @Volatile
        //variable INSTANCE keeps a reference to database returned via getInstance
        private var INSTANCE: ParlamentMemberDataBase? = null

        //Funtion for returning the database if it already has been retrieved
        //and making one new database if the instance is null
        fun getInstance(context: Context): ParlamentMemberDataBase {


            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            //initializin the database once using synchronized method,
            //so only one thread can ask for the database
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParlamentMemberDataBase::class.java, "database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}


