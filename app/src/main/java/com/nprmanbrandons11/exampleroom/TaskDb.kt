package com.nprmanbrandons11.exampleroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [taskToDo::class],
    version = 1
)

abstract class TaskDb:RoomDatabase() {
    abstract fun taskDao():TaskDao
}