package com.muchbeer.routes

import com.muchbeer.repository.UserRepository
import com.muchbeer.models.CreateUserParams
import com.muchbeer.utilss.BaseResponse
import com.muchbeer.utilss.BaseResponse.*
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.authRoutes(repository : UserRepository) {

    // Starting point for a Ktor app:
    routing {
        route("/auth") {
            post("/register") {
                val params  = call.receive<CreateUserParams>()
                val result  = repository.registerUser(params)

                call.respond(status = result.httpsStatus, result)
            }
        }

        get("/users") {

            val resultUsers = repository.retrieveUsers()
            call.respond(status = resultUsers.httpsStatus, resultUsers)
        }


        get("/findUser/{user}") {
            val user = call.parameters["user"]

            //if it is only integer please us below
            val userId = call.parameters["user"]?.toIntOrNull()
            val receiveEmail = repository.retrieveUserByMail(user)
            if (user == null) {
               call.respond(status = receiveEmail.httpsStatus,
                   message = "Need to input value here")
                return@get
            }
            else   call.respond(status = receiveEmail.httpsStatus, message = receiveEmail)
        }


        get("/index") {
            call.respondText {
                "George Machibya is so luck"
            }
        }
    }
    routing {
    }
}
