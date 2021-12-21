package com.nprmanbrandons11.exampleroom

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nprmanbrandons11.exampleroom.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskList:MutableList<taskToDo>
    private lateinit var adapter: RecyclerAdapter
    //val app = applicationContext as TaskApp
    lateinit var  room : TaskDb
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskList = mutableListOf(
        )

        lifecycleScope.launch{
             room = Room
                .databaseBuilder(application.applicationContext,TaskDb::class.java,"task")
                .build()
            room.taskDao().insert(taskList)
            taskList =room.taskDao().getAll()
            initRecycler()
        }

        binding.button.setOnClickListener {
            index++
            val task1 = taskToDo(0,binding.editTextTextPersonName.text.toString(),false)
            taskList.add(task1)
            adapter.notifyItemInserted(index);
            binding.editTextTextPersonName.text.clear()
            lifecycleScope.launch {
                room.taskDao().insert(mutableListOf(task1))
            }

        }
        binding.button2.setOnClickListener {
            lifecycleScope.launch {

                room.taskDao().update(taskList)
                val oldlist = taskList
                taskList.clear()
                val newlist = room.taskDao().getAll()
                newlist.forEach {
                    if (!it.checked){
                    taskList.add(it)}
                    else{
                        room.taskDao().delete(mutableListOf(it))
                    }
                }



                adapter.notifyDataSetChanged()
                index = taskList.size
            }
        }

    }

    fun initRecycler(){

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        index = taskList.size
        adapter =RecyclerAdapter(taskList)
        binding.recyclerView.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
      // room.taskDao().insert(taskList)
    }
}