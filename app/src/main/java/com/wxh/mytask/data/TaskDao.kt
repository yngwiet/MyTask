package com.wxh.mytask.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wxh.mytask.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<Task>>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

}