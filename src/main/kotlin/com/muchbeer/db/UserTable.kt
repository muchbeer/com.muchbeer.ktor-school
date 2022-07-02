package com.muchbeer.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("gadiel") {

    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name", 245)
    val avatar = text("avatar")
    val email = varchar("email", 234)
    val password = text("password")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now()}

            override val primaryKey = PrimaryKey(id)
}