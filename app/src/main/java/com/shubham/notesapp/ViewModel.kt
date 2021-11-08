package com.shubham.notesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.notesapp.model.RegisterBody
import com.shubham.notesapp.model.RegisterResponse
import com.shubham.notesapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(private val repository: Repository) : ViewModel() {

    var registerUserResponse: MutableLiveData<Response<RegisterResponse>> = MutableLiveData()

   /* fun registerUser(registerBody: RegisterBody) {
        viewModelScope.launch {
            val response = repository.registerUser(registerBody)
            registerUserResponse.value = response
        }
    }*/

    fun registerUser(name: String,
                     email: String,
                     password: String) {
        viewModelScope.launch {
            val response = repository.registerUser(name, email, password)
            registerUserResponse.value = response
        }
    }
}