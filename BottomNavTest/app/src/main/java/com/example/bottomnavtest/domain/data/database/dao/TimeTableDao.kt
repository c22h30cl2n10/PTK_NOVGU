package com.example.bottomnavtest.domain.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bottomnavtest.domain.data.database.TimeTable
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeTableDao {
    @Upsert
    suspend fun upsertTimeTable(timeTable: TimeTable)

    @Delete
    suspend fun deleteTimeTable(timeTable: TimeTable)

    @Query("SELECT * FROM timeTable ORDER BY id DESC")
    fun getTimeTableOrderByIdDesc(): Flow<List<TimeTable>>

    @Query("SELECT * FROM timeTable WHERE `group` = :group ORDER BY timeTableNumber")
    fun getTimeTableForGroup(group: String): Flow<List<TimeTable>>

    @Query("SELECT * FROM timeTable WHERE `teacher` = :teacher ORDER BY timeTableNumber")
    fun getTimeTableForTeacher(teacher: String): Flow<List<TimeTable>>

    @Query("SELECT * FROM timeTable WHERE `auditorium` = :auditorium ORDER BY timeTableNumber")
    fun getTimeTableForAuditorium(auditorium: String): Flow<List<TimeTable>>
}