package com.example.bottomnavtest.domain.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


//Это для тестовых данных
data class Teacher(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
){
    fun doesMatchSearchQuery(query: String) : Boolean{
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()}${lastName.first()}",
            "${firstName.first()} ${lastName.first()}",
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}
//Это таблица

@Entity
data class Teachers(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String
)