package com.example.projecttrue.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
// Name: Johnkai Cortez
// Student id: 2012960
//Dao to retrive and add
@Dao
interface ParlamentDAO {
    //insert to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: ParlamentMemberData)

    //retrives the all parlamentmemberdata from database
    @Query("SElECT * FROM Data")
    fun getData(): LiveData<List<ParlamentMemberData>>

    //retrives all party from database to be presented
    @Query("SElECT distinct party from Data")
    fun getParty(): LiveData<List<String>>


}
