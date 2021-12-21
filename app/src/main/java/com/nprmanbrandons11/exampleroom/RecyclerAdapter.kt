package com.nprmanbrandons11.exampleroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nprmanbrandons11.exampleroom.databinding.TaskItemBinding

class RecyclerAdapter(val list: MutableList<taskToDo>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding :TaskItemBinding):RecyclerView.ViewHolder(binding.root){
        fun render(item:taskToDo){
            binding.checkBox.text = item.text
            binding.checkBox.isChecked = item.checked
            binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    item.checked = isChecked

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(TaskItemBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}