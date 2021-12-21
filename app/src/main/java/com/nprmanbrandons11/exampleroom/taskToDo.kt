package com.nprmanbrandons11.exampleroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class taskToDo(

    @PrimaryKey(autoGenerate = true)val id:Int,
    @ColumnInfo(name = "text")val text:String,
    @ColumnInfo(name = "checked")var  checked:Boolean) {

}