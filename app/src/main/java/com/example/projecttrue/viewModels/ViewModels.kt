package com.example.projecttrue.viewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.projecttrue.database.ParlamentMemberData
import com.example.projecttrue.database.ParlamentMemberDataBase
import com.example.projecttrue.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModels(application: Application) : AndroidViewModel(application) {
    /*
    val readData: LiveData<List<ParlamentMemberData>>
    private val repository: Repository


    init {
        val userDao = ParlamentMemberDataBase.getInstance(application).parlamentDAO()
        repository = Repository(userDao)
        readData = repository.getData
    }


    fun insert(user: ParlamentMemberData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMembers(user)

        }

    }

     */
    private val readAllData: LiveData<List<ParlamentMemberData>>
    private val repository: Repository

    init {
        val userDao = ParlamentMemberDataBase.getInstance(application).parlamentDAO()
        repository = Repository(userDao)
        readAllData = repository.getData
    }

    fun insert(user: ParlamentMemberData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMembers(user)
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
