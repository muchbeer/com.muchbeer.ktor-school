package com.muchbeer.models

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime


data class User(
    val id : Int,
    val fullName : String,
    val avatar : String,
    val email : String,
    val password : String,
    var authToken : String? = "",
    val createdAt : String
    )
