package com.muchbeer.servicevm

import com.muchbeer.db.DatabaseFactory.dbQuery
import com.muchbeer.db.UserTable
import com.muchbeer.db.hash
import com.muchbeer.models.CreateUserParams
import com.muchbeer.models.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement : InsertStatement<Number>? = null

        dbQuery {
            statement = UserTable.insert {
                it[fullName] = params.fullName
                it[password] = hash(params.password)
                it[email] = params.email
                it[avatar] = params.avatar

            }
        }

        return rowReturn(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String?): User? {
       val user = dbQuery {
           UserTable.select {
               UserTable.email.eq(email!!)
           }.map {
               rowReturn(it)
           }.singleOrNull()
       }

        return user
    }

    override suspend fun retrieveAllUsers(): List<User?> {
       val allUser = dbQuery {
           UserTable.selectAll().map {
               rowReturn(it)
           }
       }
    return  allUser
    }

    private fun rowReturn(row : ResultRow?) : User? {
      return  if(row ==null ) null else
            User(
                id = row[UserTable.id],
                email = row[UserTable.email],
                fullName = row[UserTable.fullName],
                password = row[UserTable.password],
                avatar = row[UserTable.avatar],
                createdAt = row[UserTable.createdAt].toString()
            )
    }
}