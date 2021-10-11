package com.example.projecttrue.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Name: Johnkai Cortez
// Student id: 2012960
// 5.10.2021

//Database entities go in this file. These are responsible for reading and writing from the database.
//ParlamentMemberData represents a parlment entity in the database.
@Parcelize
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
) : Parcelable