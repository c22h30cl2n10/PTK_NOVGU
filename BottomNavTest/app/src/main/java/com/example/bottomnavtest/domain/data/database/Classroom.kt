package com.example.bottomnavtest.domain.data.database

data class Classroom(
    val id: Int,
    val number: String
){
    fun doesMatchSearchQuery(query: String) : Boolean{
        val matchingCombinations = listOf(
            "${number}"
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}
