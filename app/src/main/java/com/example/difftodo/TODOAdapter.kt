package com.example.difftodo

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.difftodo.databinding.ActivityMainBinding
import com.example.difftodo.databinding.ItemViewTaskBinding

class TODOAdapter : ListAdapter<Task, TODOAdapter.Holder>(TODODiffUtil()) {

    private val taskList = mutableListOf<Task>()

    private var count = 1

    private lateinit var mListener: OnItemClickListener

    private var pick = -1

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    private fun updateDiff(newList: List<Task>) {
        submitList(newList)
        Log.d("MyApp", "${taskList.size}")
    }

    private fun setNewList(newList: List<Task>) {
        taskList.clear()
        taskList.addAll(newList)
        updateDiff(newList)
    }

    fun add() {
        val newTask = mutableListOf<Task>()
        newTask.addAll(taskList)
        newTask.add(Task(count, ""))
        setNewList(newTask)
        count++
    }

    fun remove() {
        val newTask = mutableListOf<Task>()
        newTask.addAll(taskList)
        when(pick == -1) {
            true -> newTask.removeLast()
            false -> {
                newTask.removeAt(pick)
                pick = -1
            }
        }
        setNewList(newTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(
            ItemViewTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(private val binding: ItemViewTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.number.text = task.num.toString()
            binding.outScript.text = task.script
            setCardViewBackground(task)
            itemView.setOnClickListener {
                mListener.onItemClick(it, absoluteAdapterPosition)
                pick = absoluteAdapterPosition
                Log.d("MyApp", "${absoluteAdapterPosition}")
                clickCard(task)
                Log.d("MyApp", "${binding.cardView.cardBackgroundColor} ${Color.GRAY}")
            }
        }

        private fun setCardViewBackground(task: Task) = when(task.click) {
            true -> {
                binding.cardView.setCardBackgroundColor(Color.GRAY)
            }
            false -> {
                binding.cardView.setCardBackgroundColor(Color.WHITE)
            }
        }

        private fun clickCard(task: Task) = when(task.click) {
            true -> {
                task.click = false
            }
            false -> {
                task.click = true
            }
        }

        private fun setCardClick() {

        }

    }

}