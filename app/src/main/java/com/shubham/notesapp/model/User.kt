package com.shubham.notesapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    val loginId: String,
    val name: String,
    val email: String,
    val password: String,
)
