package com.geektech.homework71.di

import com.geektech.homework71.data.mapper.NoteMapper
import com.geektech.homework71.data.repository.NoteRepositoryImpl
import com.geektech.homework71.domain.repository.NoteRepository
import com.geektech.homework71.domain.use_case.AddNoteUseCase
import com.geektech.homework71.domain.use_case.DeleteNoteUseCase
import com.geektech.homework71.domain.use_case.GetAllNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    val noteMapper = NoteMapper()

    @Singleton
    @Provides
    fun provideNoteRepository(): NoteRepository {
        return NoteRepositoryImpl(noteMapper)
    }
}