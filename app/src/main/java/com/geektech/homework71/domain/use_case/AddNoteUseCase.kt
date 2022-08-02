package com.geektech.homework71.domain.use_case

import android.util.Log
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    val noteRepository: NoteRepository
) {

    fun addNote(note: Note){
        noteRepository.addNote(note)
    }
}