package com.suraj.roomdm3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "tasks", primaryKeys = ["id"])
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "task_title")
    var title: String,
    @ColumnInfo("task_priority")
    var priority: String,
    @ColumnInfo("is_completed")
    var isCompleted: Boolean = false
) : Serializable
