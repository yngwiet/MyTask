package com.wxh.mytask.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.wxh.mytask.R
import com.wxh.mytask.data.Task

// TODO: 2020-08-23 pass viewmodel 
class TasksListAdapter : RecyclerView.Adapter<TasksListAdapter.TaskViewHolder>() {

    private var mTasks: List<Task> = emptyList()

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.task_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val taskItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.tasks_recyclerview_item, parent, false)
        return TaskViewHolder(taskItemView)
    }

    override fun getItemCount(): Int {
        return mTasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.taskCheckBox.text = mTasks[position].title
        holder.taskCheckBox.isChecked = mTasks[position].completed
    }

    fun setTasks(tasks: List<Task>) {
        mTasks = tasks
        notifyDataSetChanged()
    }
}