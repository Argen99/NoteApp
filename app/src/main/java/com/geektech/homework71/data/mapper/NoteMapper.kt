package com.geektech.homework71.data.mapper

import com.geektech.homework71.data.model.NoteEntity
import com.geektech.homework71.domain.model.Note

class NoteMapper {

    fun toNoteEntity(note: Note): NoteEntity {
        return NoteEntity(
            id = note.id,
            title = note.title,
            description = note.description)
    }

    fun toNote(noteEntity: NoteEntity): Note{
        return Note(
            id = noteEntity.id,
            title = noteEntity.title,
            description = noteEntity.description
        )
    }
}