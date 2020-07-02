package com.wxh.mytask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wxh.mytask.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private val newTaskActivityRequestCode = 1

    private lateinit var binding: FragmentTasksBinding
    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasks, container, false)

        val recyclerView = binding.tasksRecyclerview
        val adapter = TasksListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        tasksViewModel.allTasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setTasks(it)
            }
        })

        val fab = binding.addTaskFab
        fab.setOnClickListener {
            val intent = Intent(context, NewTaskActivity::class.java)
            startActivityForResult(intent, newTaskActivityRequestCode)
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newTaskActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra(NewTaskActivity.EXTRA_TITLE) ?: getString(R.string.no_title)
            val detail = data?.getStringExtra(NewTaskActivity.EXTRA_DETAIL) ?: getString(R.string.no_detail)
            tasksViewModel.insert(Task(title, detail))
        }
    }
}
