package com.geektech.homework71.domain.use_case

import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    fun deleteNote(index: Int) = noteRepository.deleteNote(index)
}