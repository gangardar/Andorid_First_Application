package com.example.tutorials

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class TodoAdapter(
    val todoList : List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvTodo : TextView = itemView.findViewById(R.id.tvTodos)
        val cbTodo : CheckBox = itemView.findViewById(R.id.cbTodos)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_todo,parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
            holder.tvTodo.text = todoList[position].text
            holder.cbTodo.isChecked = todoList[position].isChecked
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}