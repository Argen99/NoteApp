package com.geektech.homework71.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geektech.homework71.data.repository.NoteRepositoryImpl
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.use_case.AddNoteUseCase
import com.geektech.homework71.domain.use_case.DeleteNoteUseCase
import com.geektech.homework71.domain.use_case.GetAllNotesUseCase

class MainViewModel: ViewModel() {

    private val repository = NoteRepositoryImpl()
    val getAllNotesUseCase = GetAllNotesUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)

    val liveData = MutableLiveData<List<Note>>()

    fun addNote(note: Note){
        addNoteUseCase.addNote(note)
        getAllNotes()
    }
    fun deleteNote(index: Int){
        deleteNoteUseCase.deleteNote(index)
        getAllNotes()
    }

    fun getAllNotes() {
        liveData.value = getAllNotesUseCase.getAllNotes()
    }
}