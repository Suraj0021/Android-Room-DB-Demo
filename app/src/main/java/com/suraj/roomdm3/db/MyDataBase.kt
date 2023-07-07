package com.suraj.roomdm3.db


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}