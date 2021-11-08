package com.shubham.notesapp.repository

import com.shubham.notesapp.api.RetrofitInstance
import com.shubham.notesapp.model.RegisterBody
import com.shubham.notesapp.model.RegisterResponse
import retrofit2.Response

class Repository {

   /* suspend fun registerUser(registerBody: RegisterBody): Response<RegisterResponse> {
        return RetrofitInstance.api.registerUser(registerBody)
    }*/

    suspend fun registerUser(name: String,
                             email: String,
                             password: String) : Response<RegisterResponse>{
        return RetrofitInstance.api.registerUser(name, email, password)
    }
}