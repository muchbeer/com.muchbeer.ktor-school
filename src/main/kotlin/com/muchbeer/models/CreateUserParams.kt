package com.muchbeer.models

data class CreateUserParams(
    val fullName : String,
    val avatar : String,
    val email : String,
    val password : String
)
