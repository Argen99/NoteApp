package com.geektech.homework71.domain.use_case

import com.geektech.homework71.domain.repository.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {

    fun getAllNotes() = noteRepository.getAllNote()

}