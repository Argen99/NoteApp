package com.geektech.homework71.domain.repository

import com.geektech.homework71.core.Resource
import com.geektech.homework71.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun addNote(note: Note): Flow<Resource<Boolean>>
    fun getAllNote(): Flow<Resource<List<Note>>>
    fun deleteNote(note: Note): Flow<Resource<Boolean>>
    fun updateNote(note: Note): Flow<Resource<Boolean>>
}