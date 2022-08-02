package com.geektech.homework71.presentation.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geektech.homework71.data.repository.NoteRepositoryImpl
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.domain.use_case.AddNoteUseCase
import com.geektech.homework71.domain.use_case.DeleteNoteUseCase
import com.geektech.homework71.domain.use_case.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val liveData = MutableLiveData<List<Note>>()

    fun addNote(note: Note) {
        addNoteUseCase.addNote(note)
        getAllNotes()
    }

    fun deleteNote() {
        deleteNoteUseCase.deleteNote()
        getAllNotes()
    }

    fun getAllNotes() {
        liveData.value = getAllNotesUseCase.getAllNotes()
    }
}