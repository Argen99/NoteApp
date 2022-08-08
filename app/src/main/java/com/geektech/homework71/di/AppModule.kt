package com.geektech.homework71.di

import android.content.Context
import androidx.room.Room
import com.geektech.homework71.data.mapper.NoteMapper
import com.geektech.homework71.data.repository.NoteRepositoryImpl
import com.geektech.homework71.data.room.NoteDao
import com.geektech.homework71.data.room.NoteDataBase
import com.geektech.homework71.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            "note_database"
        ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDataBase: NoteDataBase) = noteDataBase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }
}