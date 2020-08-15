package com.wxh.mytask.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.wxh.mytask.data.Task
import com.wxh.mytask.data.TaskRepository
import com.wxh.mytask.data.TaskRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository

    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = TaskRoomDatabase.getDatabase(application, viewModelScope).taskDao()
        taskRepository = TaskRepository(taskDao)
        allTasks = taskRepository.allTasks
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.insert(task)
    }

    fun delete(task: Task) = taskRepository.delete(task)

    fun update(task: Task) = taskRepository.update(task)
}