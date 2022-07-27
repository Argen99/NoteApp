package com.geektech.homework71.data.mapper

import com.geektech.homework71.data.model.NoteDto
import com.geektech.homework71.domain.model.Note

class NoteMapper {

    fun toNoteDto(note: Note): NoteDto {
        return NoteDto(
            title = note.title,
            description = note.description)
    }

    fun toNote(noteDto: NoteDto): Note{
        return Note(
            title = noteDto.title,
            description = noteDto.description
        )
    }
}