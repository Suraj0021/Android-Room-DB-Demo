package com.suraj.roomdm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.suraj.roomdm3.databinding.ActivityAddTaskBinding
import com.suraj.roomdm3.db.MyDatabase
import com.suraj.roomdm3.db.Task

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.fabAddTask.setOnClickListener {

            val roomDb: MyDatabase = Room.databaseBuilder(this, MyDatabase::class.java, "task_database")
                .allowMainThreadQueries()
                .build()

            val taskDao = roomDb.getTaskDao()

            val lastTaskId = taskDao.getLastTaskId()

            var isComplete = binding.chkIsComplete.isChecked


            taskDao.addTask(

                Task(
                    lastTaskId+1,
                    binding.edtTaskName.text.toString().replace("title",""),
                    binding.edtTaskPriority.text.toString(),
                    isComplete
                )
            )

            roomDb.close()
            Toast.makeText(this@AddTaskActivity, "save", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@AddTaskActivity,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@AddTaskActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}