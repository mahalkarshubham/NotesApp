package com.shubham.notesapp.api

import com.shubham.notesapp.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SimpleApi {

    @POST("user_registration.php")
    suspend fun registerUser(@Body user: User): Response<User>
}