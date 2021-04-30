package com.wxh.productive.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wxh.productive.data.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Query("UPDATE task_table SET completed = :completed WHERE id = :id")
    suspend fun updateCompleted(id: Int, completed: Boolean)

}