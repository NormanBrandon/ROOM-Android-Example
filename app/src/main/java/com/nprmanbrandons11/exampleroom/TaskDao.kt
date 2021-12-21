package com.nprmanbrandons11.exampleroom

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskToDo")
    suspend fun getAll():MutableList<taskToDo>
    @Insert
    suspend fun insert(tasks:MutableList<taskToDo>)
    @Delete
    suspend fun delete(tasks: MutableList<taskToDo>)
    @Update
    suspend fun update(tasks: MutableList<taskToDo>)
}