package com.wxh.mytask

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewTaskActivity : AppCompatActivity() {

    private lateinit var editTaskTitleView: EditText
    private lateinit var editTaskDetailView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        editTaskTitleView = findViewById(R.id.edit_task_title)
        editTaskDetailView = findViewById(R.id.edit_task_detail)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTaskTitleView.text)) {
                return@setOnClickListener
            } else {
                val title = editTaskTitleView.text.toString()
                val detail = editTaskDetailView.text ?: ""
                replyIntent.putExtra(EXTRA_TITLE, title)
                replyIntent.putExtra(EXTRA_DETAIL, detail)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_TITLE = "com.wxh.mytask.EXTRA_TITLE"
        const val EXTRA_DETAIL = "com.wxh.mytask.EXTRA_DETAIL"
    }
}