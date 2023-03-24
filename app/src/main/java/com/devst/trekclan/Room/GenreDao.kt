package com.devst.trekclan.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devst.trekclan.dataapi.GenreDataClasses.Tag


@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tag: Tag)

    @Query("SELECT * FROM generes")   //table name
    suspend fun getAllTags(): List<Tag>

}