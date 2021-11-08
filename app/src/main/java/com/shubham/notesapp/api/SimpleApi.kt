package com.shubham.notesapp.api

import com.shubham.notesapp.model.RegisterBody
import com.shubham.notesapp.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

   /* @POST("user_registration.php")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun registerUser(@Body registerBody: RegisterBody): Response<RegisterResponse>*/

    @FormUrlEncoded
    @POST("user_registration.php")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<RegisterResponse>

}