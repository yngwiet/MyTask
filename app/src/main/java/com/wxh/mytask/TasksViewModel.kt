package com.wxh.mytask

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: MyTaskApplication) : AndroidViewModel(application) {

    private val taskRepository: TaskRepository

    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = TaskRoomDatabase.getDatabase(application).taskDao()
        taskRepository = TaskRepository(taskDao)
        allTasks = taskRepository.allTasks
    }

    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.insert(task)
    }

}