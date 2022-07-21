package com.example.difftodo

import androidx.recyclerview.widget.DiffUtil

class TODODiffUtil : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.num == newItem.num

}