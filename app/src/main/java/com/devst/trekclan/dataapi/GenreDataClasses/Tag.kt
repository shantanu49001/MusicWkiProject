package com.devst.trekclan.dataapi.GenreDataClasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "generes")
data class Tag(

    val count: String,


    val name: String,


    val url: String
)
