package com.geektech.homework71.data.repository

import com.geektech.homework71.core.Resource
import com.geektech.homework71.data.base.BaseRepository
import com.geektech.homework71.data.mapper.NoteMapper
import com.geektech.homework71.data.network.CharacterDto
import com.geektech.homework71.data.network.MainResultDto
import com.geektech.homework71.data.network.RickAndMortyApiService
import com.geektech.homework71.data.room.NoteDao
import com.geektech.homework71.domain.model.network.Character
import com.geektech.homework71.domain.model.network.MainResult
import com.geektech.homework71.domain.model.room.Note
import com.geektech.homework71.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val apiService: RickAndMortyApiService
) : NoteRepository, BaseRepository() {
    private val noteMapper = NoteMapper()

//    override fun getAllCharacters(): Flow<Resource<MainResult<Character>>> = doRequest {
//        noteMapper.toMainResult(apiService.getAllCharacters())
//    }

    override fun addNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.addNote(noteMapper.toNoteEntity(note))
    }

    override fun getAllNote(): Flow<Resource<List<Note>>> = doRequest {
        noteDao.getAllNotes().map { noteEntity ->
            noteMapper.toNote(noteEntity)
        }
    }

    override fun deleteNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.deleteNote(noteMapper.toNoteEntity(note))
    }

    override fun updateNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.updateNote(noteMapper.toNoteEntity(note))
    }

}