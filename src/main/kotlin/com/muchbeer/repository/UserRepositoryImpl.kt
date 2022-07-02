package com.muchbeer.repository

import com.muchbeer.models.CreateUserParams
import com.muchbeer.security.JwtConfig
import com.muchbeer.servicevm.UserService
import com.muchbeer.utilss.BaseResponse

class UserRepositoryImpl(private val userService: UserService) : UserRepository {
    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
     return   if(isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = "User already exist")
        } else {
            val user = userService.registerUser(params)
            if (user != null) {

                val token = JwtConfig.instance.createAccessToken(user.id)
                BaseResponse.SuccessResponse(user.copy(authToken = token))
            } else BaseResponse.ErrorResponse()
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveUsers(): BaseResponse<Any> {
        val alluser = userService.retrieveAllUsers()
       return if (alluser.isNotEmpty()) {
            BaseResponse.SuccessResponse(alluser)
        } else BaseResponse.ErrorResponse(message = "No list obtained list found")
    }

    private suspend fun isEmailExist(email : String) : Boolean {
       return userService.findUserByEmail(email) !=null
    }

}