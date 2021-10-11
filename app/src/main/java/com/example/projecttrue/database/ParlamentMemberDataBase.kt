package com.example.projecttrue.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//Make the Database
@Database(entities = [ParlamentMemberData::class], version = 1, exportSchema = false)
abstract class ParlamentMemberDataBase : RoomDatabase() {

    abstract fun parlamentDAO(): ParlamentDAO

    companion object {
        @Volatile
        private var INSTANCE: ParlamentMemberDataBase? = null
        fun getInstance(context: Context): ParlamentMemberDataBase {
                val instance = INSTANCE
                if (instance != null) {
                    return instance
                }
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


