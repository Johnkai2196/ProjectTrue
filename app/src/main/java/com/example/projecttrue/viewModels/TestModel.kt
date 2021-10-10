package com.example.projecttrue.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projecttrue.database.ParlamentDAO
import com.example.projecttrue.database.ParlamentMemberDataBase.Companion.getInstance
import com.example.projecttrue.repository.Repository
import kotlinx.coroutines.launch

class TestModel (application: Application) : AndroidViewModel(application) {

}