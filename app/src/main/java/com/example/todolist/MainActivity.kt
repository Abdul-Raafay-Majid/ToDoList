package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = ToDoAdapter(mutableListOf())
        recycler_view.adapter = todoAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        add_button.setOnClickListener {
            val toDoTitle = editText.text.toString()
            if(toDoTitle.isNotEmpty()) {
                val todo = ToDo(toDoTitle)
                todoAdapter.addToDo(todo)
                editText.text.clear()
            }
        }
        btn_delete.setOnClickListener { todoAdapter.deleteDoneToDo() }


    }
}

