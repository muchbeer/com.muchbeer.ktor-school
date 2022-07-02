package com.muchbeer.routes

import com.muchbeer.repository.UserRepository
import com.muchbeer.models.CreateUserParams
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

        get("/index") {
            call.respondText {
                "George Machibya is so luck"
            }
        }
    }
    routing {
    }
}
