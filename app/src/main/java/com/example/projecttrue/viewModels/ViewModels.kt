package com.example.projecttrue.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projecttrue.database.CommentData
import com.example.projecttrue.database.CommentDataBase
import com.example.projecttrue.database.ParlamentMemberData
import com.example.projecttrue.database.ParlamentMemberDataBase
import com.example.projecttrue.network.ParlamentApi
import com.example.projecttrue.repository.CommentRepository
import com.example.projecttrue.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Name: Johnkai Cortez
// Student id: 2012960
// 5.10.2021
class ViewModels(application: Application) : AndroidViewModel(application) {
    //start the variable later so i can add to the database of parlament
    lateinit var members: List<ParlamentMemberData>

    //live data that i can read trough database for Parlament database
    val readAllData: LiveData<List<ParlamentMemberData>>

    //live data that i can read trough party
    val readParty: LiveData<List<String>>

    //variable for repository
    private val repository: Repository

    //database variable
    val userDao = ParlamentMemberDataBase.getInstance(application).parlamentDAO()

    //variable for repository
    private val repositoryComent: CommentRepository

    //database variable
    val commentDao = CommentDataBase.getInstance(application).CommentDAO()

    //live data that i can read trough comment database
    val readComment: LiveData<List<CommentData>>

    //starts this immediately
    init {
        //Set the variable for repositoryComent to the readComment so i can the database or add
        repositoryComent = CommentRepository(commentDao)
        readComment = repositoryComent.readComment

        //Set the variable for repositoryto the readalldata and readParty so i can the database or add
        repository = Repository(userDao)
        readAllData = repository.getData
        readParty = repository.getParty
//do this when viewmodel is launch
        viewModelScope.launch {
            getData()
            addGroup()
        }

    }

    //add to database comment
    fun addComment(userRate: CommentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryComent.addComement(userRate)
        }
    }

    //get the api info
    private suspend fun getData() {
        members = ParlamentApi.retrofitService.getProperties()
    }

    //set the api receive list and adds to database
    private suspend fun addGroup() {
        members.forEach {
            userDao.insert(it)
        }
    }

}
