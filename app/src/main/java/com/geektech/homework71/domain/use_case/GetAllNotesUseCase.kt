package com.geektech.homework71.domain.use_case

import android.util.Log
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    val noteRepository: NoteRepository
) {

    fun getAllNotes() : List<Note>{
        return noteRepository.getAllNote()
    }

}