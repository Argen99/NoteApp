package com.geektech.homework71.data.repository

import com.geektech.homework71.core.Resource
import com.geektech.homework71.data.mapper.NoteMapper
import com.geektech.homework71.data.model.NoteEntity
import com.geektech.homework71.data.room.NoteDao
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Executable
import java.util.Collections.emptyList
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    private val noteMapper = NoteMapper()

    override fun addNote(note: Note): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            noteDao.addNote(noteMapper.toNoteEntity(note))
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error("Error", false))
        }
    }

    override fun getAllNote(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            val data = noteDao.getAllNotes().map { noteEntity ->
                noteMapper.toNote(noteEntity)
            }
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error("Error", emptyList()))
        }
    }

    override fun deleteNote(note: Note): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            noteDao.deleteNote(noteMapper.toNoteEntity(note))
            emit(Resource.Success(true))
        } catch (e: Exception) {
            emit(Resource.Error("Error", false))
        }
    }

    override fun updateNote(note: Note): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            noteDao.updateNote(noteMapper.toNoteEntity(note))
            emit(Resource.Success(true))
        }catch (e: Exception) {
            emit(Resource.Error("Error",false))
        }
    }
}