package com.geektech.homework71.data.repository

import com.geektech.homework71.data.mapper.NoteMapper
import com.geektech.homework71.data.model.NoteDto
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.repository.NoteRepository

class NoteRepositoryImpl: NoteRepository {

    private val noteMapper = NoteMapper()
    private val notes = arrayListOf<NoteDto>()

    override fun addNote(note: Note) {
        notes.add(noteMapper.toNoteDto(note))
    }

    override fun getAllNote(): List<Note> {
        return notes.map { noteDto ->
            noteMapper.toNote(noteDto)
        }
    }

    override fun deleteNote(index: Int) {
        notes.removeAt(index)
    }
}