package com.suraj.roomdm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.suraj.roomdm3.databinding.ActivityTaskBinding
import com.suraj.roomdm3.db.MyDatabase
import com.suraj.roomdm3.db.Task

class TaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTaskBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val data = intent.getSerializableExtra("task") as Task

        binding.txtTask.setText("Title ${data.title}")

        binding.txtPriority.setText("Priority Level : ${data.priority}")

        if (data.isCompleted == true) binding.chkIsComplete.isChecked = true
        else binding.chkIsComplete.isChecked = false


        binding.fabDaeleteTask.setOnClickListener {

            val roomDb: MyDatabase = Room.databaseBuilder(this, MyDatabase::class.java, "task_database")
                .allowMainThreadQueries()
                .build()

            val taskDao = roomDb.getTaskDao()

            var isComplete = binding.chkIsComplete.isChecked


            taskDao.deleteTask(data.id)

            roomDb.close()
            Toast.makeText(this@TaskActivity, "save", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@TaskActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.fabSaveTask.setOnClickListener {
            val roomDb: MyDatabase = Room.databaseBuilder(this, MyDatabase::class.java, "task_database")
                .allowMainThreadQueries()
                .build()

            val taskDao = roomDb.getTaskDao()

            taskDao.update(Task(data.id,binding.txtTask.text.toString(),binding.txtPriority.text.toString(),binding.chkIsComplete.isChecked))
            val intent = Intent(this@TaskActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}