package com.muchbeer.servicevm

import com.muchbeer.models.CreateUserParams
import com.muchbeer.models.User

interface UserService {

    suspend fun registerUser(params : CreateUserParams) : User?

    suspend fun findUserByEmail(email : String) : User?

    suspend fun retrieveAllUsers() : List<User?>
}