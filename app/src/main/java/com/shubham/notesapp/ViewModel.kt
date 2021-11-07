package com.shubham.notesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.notesapp.model.User
import com.shubham.notesapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(private val repository: Repository) : ViewModel() {

    var myResponse: MutableLiveData<Response<User>> = MutableLiveData()

    fun registerUser(user: User) {
        viewModelScope.launch {
            val response = repository.registerUser(user)
            myResponse.value = response
        }
    }
}