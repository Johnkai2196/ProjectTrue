package com.example.projecttrue.database

import androidx.room.Entity
import androidx.room.PrimaryKey
//the data class
@Entity(tableName = "Data")
data class ParlamentMemberData(
    @PrimaryKey
    val personNumber: Int,
    val seatNumber: Int,
    val first: String,
    val last: String,
    val party: String,
    val minister: Boolean,
    val picture: String,
    val twitter: String,
    val bornYear: Int,
    val constituency: String
)