package com.example.bottomnavtest.domain.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bottomnavtest.domain.data.database.Teachers
import kotlinx.coroutines.flow.Flow

@Dao
interface TeachersDao {
    @Upsert
    suspend fun upsertTeachers(teachers: Teachers)

    @Delete
    suspend fun deleteTeachers(teachers: Teachers)

    @Query("SELECT * FROM teachers ORDER BY id ASC")
    fun getTeachersOrderByIdAsc(): Flow<List<Teachers>>
}