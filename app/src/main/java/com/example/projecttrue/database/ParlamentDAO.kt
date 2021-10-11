package com.example.projecttrue.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ParlamentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: ParlamentMemberData)

    @Query("SElECT * FROM Please_work")
    fun getData(): LiveData<List<ParlamentMemberData>>

    @Query("SElECT distinct party from Please_work")
    fun getParty(): LiveData<List<String>>
}
