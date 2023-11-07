package com.example.bottomnavtest.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bottomnavtest.domain.data.database.dao.AuditoriumsDao
import com.example.bottomnavtest.domain.data.database.dao.GroupsDao
import com.example.bottomnavtest.domain.data.database.dao.TeachersDao
import com.example.bottomnavtest.domain.data.database.dao.TimeTableDao

@Database(
    entities = [TimeTable::class, Auditoriums::class, Groups::class, Teachers::class],
    version = 1
)
abstract class TimeTableDatabase: RoomDatabase() {
    abstract val timeTableDao: TimeTableDao
    abstract val auditoriumsDao: AuditoriumsDao
    abstract val teachersDao: TeachersDao
    abstract val groupsDao: GroupsDao
    //С гайда от Google
    /*companion object{
        @Volatile
        private var Instance: TimeTableDatabase? = null

        fun getDatabase(context: Context): TimeTableDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    TimeTableDatabase::class.java,
                    "timetable_database"
                ).build().also { Instance = it }
            }
        }
    }*/
}