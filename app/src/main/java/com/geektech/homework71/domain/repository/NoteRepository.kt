package com.geektech.homework71.domain.repository

import com.geektech.homework71.core.Resource
import com.geektech.homework71.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(note: Note): Flow<Resource<Boolean>>
    suspend fun getAllNote(): Flow<Resource<List<Note>>>
    suspend fun deleteNote(): Flow<Resource<Boolean>>
}