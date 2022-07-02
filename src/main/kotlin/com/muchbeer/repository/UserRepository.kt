package com.muchbeer.repository

import com.muchbeer.models.CreateUserParams
import com.muchbeer.utilss.BaseResponse

interface UserRepository {
    suspend fun registerUser(params : CreateUserParams) : BaseResponse<Any>
    suspend fun loginUser(email : String, password: String) : BaseResponse<Any>
    suspend fun retrieveUsers() : BaseResponse<Any>
}