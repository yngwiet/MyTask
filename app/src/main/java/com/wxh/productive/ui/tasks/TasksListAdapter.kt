package com.wxh.productive.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wxh.productive.data.model.Task
import com.wxh.productive.databinding.TasksRecyclerviewItemBinding

class TasksListAdapter(
    private val onTaskChecked: (id: Int, isChecked: Boolean) -> Unit
) : ListAdapter<Task, TasksListAdapter.TaskViewHolder>(DiffCallback) {

    inner class TaskViewHolder(
        binding: TasksRecyclerviewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val taskCheckBox: CheckBox = binding.checkboxTask
        private val taskTitle: TextView = binding.textViewTask

        fun bind(task: Task) {
            taskTitle.text = task.title
            taskCheckBox.setOnCheckedChangeListener(null)
            taskCheckBox.isChecked = task.completed
            taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
                onTaskChecked(task.id, isChecked)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TasksRecyclerviewItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

}