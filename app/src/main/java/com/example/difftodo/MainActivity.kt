package com.example.difftodo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.difftodo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val todoAdapter = TODOAdapter()

    private var mPosition = -1

    private val binding : ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val mView: View by lazy {
        binding.mainLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.taskListView.adapter = todoAdapter
        val lm = LinearLayoutManager(this)
        binding.taskListView.layoutManager = lm
        binding.taskListView.setHasFixedSize(true)

        todoAdapter.setOnItemClickListener(object: TODOAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                mPosition = position
            }
        })

        binding.addBtn.setOnClickListener { todoAdapter.add() }
        binding.deleteBtn.setOnClickListener { todoAdapter.remove() }
    }

}