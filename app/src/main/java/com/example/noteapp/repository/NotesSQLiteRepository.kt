package com.example.noteapp.repository

import androidx.room.Room
import com.example.noteapp.NoteApplication
import com.example.noteapp.data_source.DB
import com.example.noteapp.data_source.Note
import kotlinx.coroutines.flow.Flow
import java.util.*


object NotesSQLiteRepository {

    private val db = DB(NoteApplication.context)

    suspend fun getAllNotes(): List<Note> = db.getAllNotes()

    suspend fun create(title: String, note: String) {
        db.addNote(title, note)
    }

    suspend fun update(note: Note) {
        db.update(note)
    }

    suspend fun deleteNote(note: Note) {
        db.delete(note)
    }

    suspend fun get(id: Long): Note? = db.getNote(id)

}