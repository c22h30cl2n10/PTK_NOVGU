package com.example.bottomnavtest.domain.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.bottomnavtest.domain.data.database.Groups
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupsDao {
    @Upsert
    suspend fun upsertGroups(groups: Groups)

    @Delete
    suspend fun deleteGroups(groups: Groups)

    @Query("SELECT * FROM groups ORDER BY id ASC")
    fun getGroupsOrderByIdAsc(): Flow<List<Groups>>

    @Query("SELECT * FROM groups ORDER BY number ASC")
    fun getGroupsOrderByNumberAsc(): Flow<List<Groups>>

    @Query("SELECT * FROM groups WHERE number LIKE :pattern || '%'")
    fun getGroupsByKoursSortByNumber(pattern: Int): Flow<List<Groups>>
}