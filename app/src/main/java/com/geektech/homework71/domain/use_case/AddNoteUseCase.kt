package com.geektech.homework71.domain.use_case

import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.repository.NoteRepository

class AddNoteUseCase(private val noteRepository: NoteRepository) {

    fun addNote(note: Note) = noteRepository.addNote(note)
}