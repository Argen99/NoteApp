package com.geektech.homework71.domain.use_case

import com.geektech.homework71.domain.model.room.Note
import com.geektech.homework71.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase@Inject constructor(
    val noteRepository: NoteRepository
) {

    fun updateNote(note: Note) = noteRepository.updateNote(note)
}