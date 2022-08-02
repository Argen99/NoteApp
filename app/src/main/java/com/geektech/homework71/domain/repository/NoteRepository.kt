package com.geektech.homework71.domain.repository

import com.geektech.homework71.domain.model.Note

interface NoteRepository {

    fun addNote(note: Note)
    fun getAllNote(): List<Note>
    fun deleteNote()
}