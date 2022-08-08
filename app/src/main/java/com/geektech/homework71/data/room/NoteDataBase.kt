package com.geektech.homework71.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektech.homework71.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}