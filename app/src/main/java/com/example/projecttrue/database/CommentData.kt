package com.example.projecttrue.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
// Name: Johnkai Cortez
// Student id: 2012960

//Database entities go in this file. These are responsible for reading and writing from the database.
//CommentData represents a comment entity in the database.
@Parcelize
@Entity(tableName = "rating")
data class CommentData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val personNumber: Int,
    val comment: String,
    val rating: Int
) : Parcelable