package com.muchbeer

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.muchbeer.db.DatabaseFactory
import com.muchbeer.repository.UserRepository
import com.muchbeer.repository.UserRepositoryImpl
import com.muchbeer.routes.*
import com.muchbeer.security.configureSecurity
import com.muchbeer.servicevm.UserService
import com.muchbeer.servicevm.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {

    embeddedServer(Netty, port = 2016, host = "127.0.0.2") {
        DatabaseFactory.init()
        install(ContentNegotiation) {
         jacksonObjectMapper()
            //jackson()
            jackson {
                setDefaultPrettyPrinter(DefaultPrettyPrinter())
            }
        }

        configureSecurity()

        val service :UserService = UserServiceImpl()
        val repository : UserRepository = UserRepositoryImpl(service)
        authRoutes(repository)
    }.start(wait = true)
}

