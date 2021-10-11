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
class ViewModels(application: Application) : AndroidViewModel(application) {
    lateinit var members: List<ParlamentMemberData>

    val readAllData: LiveData<List<ParlamentMemberData>>
    val readParty: LiveData<List<String>>

    private val repository: Repository
    val userDao = ParlamentMemberDataBase.getInstance(application).parlamentDAO()

    private val repositoryComent: CommentRepository
    val commentDao = CommentDataBase.getInstance(application).CommentDAO()
    val readComment: LiveData<List<CommentData>>

    init {
        repositoryComent = CommentRepository(commentDao)
        readComment = repositoryComent.readComment

        repository = Repository(userDao)
        readAllData = repository.getData
        readParty = repository.getParty

        viewModelScope.launch {
            getData()
            addGroup()
        }

    }

    fun addComment(userRate: CommentData) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryComent.addComement(userRate)
        }
    }

    private suspend fun getData() {
        members = ParlamentApi.retrofitService.getProperties()
    }

    private suspend fun addGroup() {
        members.forEach {
            userDao.insert(it)
        }
    }

}
