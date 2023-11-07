package com.example.bottomnavtest.domain.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Group(
    val id: Int,
    val number: String
)

@Entity(tableName = "groups")
data class Groups(
    @PrimaryKey
    val id: Int,
    val number: String
)