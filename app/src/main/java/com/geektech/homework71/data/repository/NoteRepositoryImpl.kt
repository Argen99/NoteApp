package com.geektech.homework71.data.repository

import com.geektech.homework71.core.Resource
import com.geektech.homework71.data.mapper.NoteMapper
import com.geektech.homework71.data.model.NoteDto
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor (
    private val noteMapper: NoteMapper): NoteRepository {
    private val notes = arrayListOf<NoteDto>()

    override suspend fun addNote(note: Note):Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            notes.add(noteMapper.toNoteDto(note))
            emit(Resource.Success(true))
        }catch (e: Exception){
            emit(Resource.Error("Error", false))
        }
    }

    override suspend fun getAllNote(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            val data = notes.map { noteDto ->
                noteMapper.toNote(noteDto)
            }
            emit(Resource.Success(data))
        }catch (e: Exception){
            emit(Resource.Error("Error", emptyList()))
        }
    }

    override suspend fun deleteNote():Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            notes.removeLast()
            emit(Resource.Success(true))
        }catch (e: Exception){
            emit(Resource.Error("Error",false))
        }
    }
}