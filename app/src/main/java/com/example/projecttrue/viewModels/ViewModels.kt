package com.example.projecttrue.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projecttrue.database.ParlamentMemberData
import com.example.projecttrue.database.ParlamentMemberDataBase
import com.example.projecttrue.network.ParlamentApi
import com.example.projecttrue.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModels(application: Application) : AndroidViewModel(application) {
    lateinit var members: List<ParlamentMemberData>
    val readAllData: LiveData<List<ParlamentMemberData>>
    val readParty: LiveData<List<String>>
    private val repository: Repository
    val userDao = ParlamentMemberDataBase.getInstance(application).parlamentDAO()


    init {
        repository = Repository(userDao)
        readAllData = repository.getData
        readParty = repository.getParty
        viewModelScope.launch {
                getData()
                addGroup()
            
        }

    }

    fun insert(user: ParlamentMemberData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMembers(user)
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


    /* class Factory(val app: Application) : ViewModelProvider.Factory {
         override fun <T : ViewModel?> create(modelClass: Class<T>): T {
             if (modelClass.isAssignableFrom(ViewModels::class.java)) {
                 @Suppress("UNCHECKED_CAST")
                 return ViewModels(app) as T
             }
             throw IllegalArgumentException("Unable to construct viewmodel")
         }
     }

     */
}
