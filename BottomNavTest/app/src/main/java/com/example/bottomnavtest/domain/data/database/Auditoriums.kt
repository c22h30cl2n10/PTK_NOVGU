package com.example.bottomnavtest.domain.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auditoriums")
data class Auditoriums(
    @PrimaryKey
    val id: Int,
    val number: String
)
