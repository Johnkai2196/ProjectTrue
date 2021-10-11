package com.example.projecttrue.repository

import androidx.lifecycle.LiveData
import com.example.projecttrue.database.ParlamentDAO
import com.example.projecttrue.database.ParlamentMemberData

// Name: Johnkai Cortez
// Student id: 2012960
// 5.10.2021
class Repository(dao: ParlamentDAO) {
    //getting the Parlament data from database
    val getData: LiveData<List<ParlamentMemberData>> = dao.getData()

    //getting the Parlament party so i can make a recylerview without duplicates
    val getParty: LiveData<List<String>> = dao.getParty()

}