package com.example.projecttrue.repository

import androidx.lifecycle.LiveData
import com.example.projecttrue.database.CommentDAO
import com.example.projecttrue.database.CommentData

// Name: Johnkai Cortez
// Student id: 2012960
// 11.10.2021
class CommentRepository(private val comment: CommentDAO) {
    //get the Comment data from database so i can display it
    val readComment: LiveData<List<CommentData>> = comment.readComment()

    //i can add comments
    suspend fun addComement(data: CommentData) {
        comment.insertComment(data)
    }
}