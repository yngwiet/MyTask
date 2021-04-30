package com.wxh.productive.ui.viewmodel.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.wxh.productive.data.db.ProductiveDatabase
import com.wxh.productive.data.model.Task
import com.wxh.productive.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TasksViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository

    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = ProductiveDatabase.getDatabase(application, viewModelScope).taskDao()
        taskRepository = TaskRepository(taskDao)
        allTasks = taskRepository.allTasks
    }

    fun insert(task: Task) = viewModelScope.launch {
        taskRepository.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        taskRepository.delete(task)
    }

    fun deleteAllTasks() = viewModelScope.launch {
        taskRepository.deleteAllTasks()
    }

    fun update(task: Task) = viewModelScope.launch {
        taskRepository.update(task)
    }

    fun changeTaskCompletedStatus(id: Int, completed: Boolean) {
        viewModelScope.launch {
            taskRepository.changeTaskCompletedStatus(id, completed)
        }
    }
}