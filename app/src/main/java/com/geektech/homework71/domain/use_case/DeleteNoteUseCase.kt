package com.geektech.homework71.domain.use_case

import com.geektech.homework71.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase
    @Inject constructor(
    val noteRepository: NoteRepository) {

    suspend fun deleteNote() = noteRepository.deleteNote()
}