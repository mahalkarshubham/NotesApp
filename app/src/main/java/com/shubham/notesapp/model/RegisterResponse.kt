package com.shubham.notesapp.model

data class RegisterResponse(
    val status: String,
    val message: String,
    val user: User
)
