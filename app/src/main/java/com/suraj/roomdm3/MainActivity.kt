package com.suraj.roomdm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.suraj.roomdm3.adapter.TaskAdapter
import com.suraj.roomdm3.databinding.ActivityMainBinding
import com.suraj.roomdm3.db.MyDatabase
import com.suraj.roomdm3.db.Task

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskList: List<Task>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        addData()

        binding.rvTaskList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        taskList = getTaskList()
        taskAdapter = TaskAdapter(taskList)
        binding.rvTaskList.adapter = taskAdapter

        taskAdapter.setOnClickListener = SetOnClickListener()

        binding.fabAddTask.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            startActivity(intent)
            finish()

        }


    }

    private fun addData() {
        val roomDb: MyDatabase = Room.databaseBuilder(this, MyDatabase::class.java, "task_database")
            .allowMainThreadQueries()
            .build()


        val taskDao = roomDb.getTaskDao()




        taskDao.addTask(
            Task(
                101,
                "Task 1",
                "2",
                true
            )
        )

        taskDao.addTask(

            Task(
                102,
                "Task 2",
                "3",
                false
            )
        )
    }

    fun getTaskList(): List<Task> {

        val roomDb: MyDatabase = Room.databaseBuilder(this, MyDatabase::class.java, "task_database")
            .allowMainThreadQueries()
            .build()


        val taskDao = roomDb.getTaskDao()


        val tasks = taskDao.getTasks()
        for (task in tasks) {
            Log.e(
                "MyDatabase",
                "Task: ID=${task.id}, Title=${task.title}, Priority=${task.priority}, Completed=${task.isCompleted}"
            )
        }

        roomDb.close()

        return tasks

    }

    inner class SetOnClickListener : TaskAdapter.SetOnClickListener {
        override fun setOnClick(position: Int) {

            val intent = Intent(this@MainActivity, TaskActivity::class.java)
            intent.putExtra("task",taskList[position])
            startActivity(intent)
            finish()

        }
    }

}