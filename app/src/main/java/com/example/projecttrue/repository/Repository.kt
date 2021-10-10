package com.example.projecttrue.repository

import androidx.lifecycle.LiveData
import com.example.projecttrue.database.ParlamentDAO
import com.example.projecttrue.database.ParlamentMemberData


class Repository(private val dao: ParlamentDAO) {

    val getData: LiveData<List<ParlamentMemberData>> = dao.getData()

  suspend fun  addMembers(members:ParlamentMemberData) {
        dao.insert(members)
    }
}