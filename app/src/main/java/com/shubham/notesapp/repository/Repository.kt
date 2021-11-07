package com.shubham.notesapp.repository

import com.shubham.notesapp.api.RetrofitInstance
import com.shubham.notesapp.model.User
import retrofit2.Response

class Repository {

    /* suspend fun getPost(): Response<UserResponse> {
         return RetrofitInstance.api.getPost()
     }*/

    suspend fun registerUser(user: User): Response<User> {
        return RetrofitInstance.api.registerUser(user)
    }
}