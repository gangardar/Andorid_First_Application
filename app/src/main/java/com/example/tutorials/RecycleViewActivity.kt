package com.example.tutorials

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecycleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var todoList = mutableListOf(
            Todo("Go to bar", false),
            Todo("Go to supermarket", false),
            Todo("Sleep for 8 hours today", true),
            Todo("Listen to positive affirmation", true),
            Todo("Watch inception movie", false),
            Todo("Walk for 30 minutes", true)
        )

        val adapter = TodoAdapter(todoList)
        val rvTodo : RecyclerView = findViewById<RecyclerView>(R.id.rvTodos)
        rvTodo.layoutManager = LinearLayoutManager(this)
        rvTodo.adapter = adapter


    }
}