package com.wxh.productive.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    var title: String,
    var detail: String,
    var completed: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {

}