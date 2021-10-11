package com.example.projecttrue.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Name: Johnkai Cortez
// Student id: 2012960
// 11.10.2021
@Dao
interface CommentDAO {
    //insert to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentData)

    //retrieve from comment database and order the by the new id
    @Query("SELECT*FROM rating ORDER BY id DESC ")
    fun readComment(): LiveData<List<CommentData>>


}