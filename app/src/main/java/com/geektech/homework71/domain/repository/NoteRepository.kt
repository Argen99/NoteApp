package com.geektech.homework71.domain.repository

import com.geektech.homework71.core.Resource
import com.geektech.homework71.data.network.CharacterDto
import com.geektech.homework71.data.network.MainResultDto
import com.geektech.homework71.domain.model.network.Character
import com.geektech.homework71.domain.model.network.MainResult
import com.geektech.homework71.domain.model.room.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun addNote(note: Note): Flow<Resource<Unit>>
    fun getAllNote(): Flow<Resource<List<Note>>>
    fun deleteNote(note: Note): Flow<Resource<Unit>>
    fun updateNote(note: Note): Flow<Resource<Unit>>

//    fun getAllCharacters(): Flow<Resource<MainResult<Character>>>
}