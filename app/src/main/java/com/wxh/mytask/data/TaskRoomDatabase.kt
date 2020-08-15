package com.wxh.mytask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    private class TaskDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val taskDao = database.taskDao()
                    val taskList: List<Task>? = taskDao.getAllTasks().value
                    if (taskList == null || taskList.isEmpty()) {
                        sampleTasks.forEach {
                            taskDao.insertTask(it)
                        }
                    }
                }
            }
        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                database.taskDao().getAllTasks()
            }
        }
    }

    companion object {

        val sampleTasks: List<Task> = listOf(
            Task("Learn", "Learn Kotlin and Android!"),
            Task("Play", "Play!")
        )

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TaskRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                ).addCallback(
                    TaskDatabaseCallback(
                        scope
                    )
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}