package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private val Tasks: MutableList<ToDo>):RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
      return ToDoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        var curTodo=Tasks[position]
        var task_text=holder.itemView.findViewById<TextView>(R.id.text_view_task)
        var task_checkBox=holder.itemView.findViewById<CheckBox>(R.id.check_box_task)
        task_text.text=curTodo.task
        task_checkBox.isChecked=curTodo.isChecked
        task_checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            toggleStrikeThrough(task_text,isChecked)
            curTodo.isChecked=!curTodo.isChecked
        }

    }

    override fun getItemCount(): Int {
        return Tasks.size
    }

    fun addToDo(task:ToDo ){
        Tasks.add(task)
        notifyItemInserted(Tasks.size-1)
    }

    fun deleteDoneToDo() {
        Tasks.removeAll { task->
            task.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(textView: TextView,isChecked:Boolean){
        if(isChecked) {
            textView.paintFlags =textView.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            textView.paintFlags =textView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    }



