package com.suraj.roomdm3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suraj.roomdm3.R
import com.suraj.roomdm3.databinding.TaskViewBinding
import com.suraj.roomdm3.db.Task

class TaskAdapter(val taskList: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {




    interface SetOnClickListener{
        fun setOnClick(position: Int)
    }

    var setOnClickListener: SetOnClickListener? = null

    fun setOnClickListener(listener: SetOnClickListener) {
        setOnClickListener = listener
    }




   inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

          var binding : TaskViewBinding = TaskViewBinding.bind(view)


        init {
            view.setOnClickListener {
               setOnClickListener?.setOnClick(adapterPosition )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_view, parent, false)
        )
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val data = taskList[position]

        holder.binding.txtTask.text = "Title ${data.title}"
        holder.binding.txtPriority.text = "Priority Level : ${data.priority}"
        holder.binding.txtIsComplete.text = "Is Complete : ${data.isCompleted}"

    }

}