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

    @Query("SElECT * FROM please_work")
    fun getData(): LiveData<List<ParlamentMemberData>>

}
