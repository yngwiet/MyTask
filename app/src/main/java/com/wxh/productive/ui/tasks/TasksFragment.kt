package com.wxh.productive.ui.tasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wxh.productive.R
import com.wxh.productive.data.model.Task
import com.wxh.productive.databinding.FragmentTasksBinding
import com.wxh.productive.ui.taskdetail.NewTaskActivity
import com.wxh.productive.ui.viewmodel.tasks.TasksViewModel

class TasksFragment : Fragment() {

    private val newTaskActivityRequestCode = 1

    private lateinit var binding: FragmentTasksBinding
    private lateinit var adapter: TasksListAdapter
    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpFab()

        subscribeToChanges()
    }

    private fun setUpRecyclerView() {
        adapter = TasksListAdapter(onTaskChecked)
        val recyclerView = binding.tasksRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setUpFab() {
        val fab = binding.addTaskFab
        fab.setOnClickListener {
            val intent = Intent(context, NewTaskActivity::class.java)
            startActivityForResult(intent, newTaskActivityRequestCode)
        }
    }

    private fun subscribeToChanges() {
        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        tasksViewModel.allTasks.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    private val onTaskChecked = { id: Int, isChecked: Boolean ->
        tasksViewModel.changeTaskCompletedStatus(id, isChecked)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newTaskActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra(NewTaskActivity.EXTRA_TITLE) ?: getString(
                R.string.no_title
            )
            val detail = data?.getStringExtra(NewTaskActivity.EXTRA_DETAIL) ?: getString(
                R.string.no_detail
            )
            tasksViewModel.insert(Task(title, detail))
        }
    }
}
