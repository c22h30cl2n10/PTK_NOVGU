package com.example.bottomnavtest.domain.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bottomnavtest.domain.data.database.Auditoriums
import kotlinx.coroutines.flow.Flow

@Dao
interface AuditoriumsDao {
    @Upsert
    suspend fun upsertAuditoriums(auditoriums: Auditoriums)

    @Delete
    suspend fun deleteAuditoriums(auditoriums: Auditoriums)

    @Query("SELECT * FROM auditoriums ORDER BY id ASC")
    fun getAuditoriumsOrderByIdAsc(): Flow<List<Auditoriums>>

    @Query("SELECT * FROM auditoriums ORDER BY number ASC")
    fun getAuditoriumsOrderByNumberAsc(): Flow<List<Auditoriums>>
}