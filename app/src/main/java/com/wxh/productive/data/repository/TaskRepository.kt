package com.wxh.productive.data.repository

import androidx.lifecycle.LiveData
import com.wxh.productive.data.db.TaskDao
import com.wxh.productive.data.model.Task

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TaskRepository(private val taskDao: TaskDao) {

    // Room executes all queries on a separate thread when return type is LiveData.
    // Observed LiveData will notify the observer when the data has changed.
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) = taskDao.insertTask(task)

    suspend fun update(task: Task) = taskDao.updateTask(task)

    suspend fun delete(task: Task) = taskDao.deleteTask(task)

    suspend fun deleteAllTasks() = taskDao.deleteAllTasks()
}