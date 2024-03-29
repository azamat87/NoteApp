package com.example.noteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data_source.Note
import com.example.noteapp.repository.NotesSQLiteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class NotesListViewModel(private val repository: NotesSQLiteRepository = NotesSQLiteRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow<List<Note>>(emptyList())
    val viewState: Flow<List<Note>> get() = _viewState

    fun getNotes() {
        viewModelScope.launch {
            val notes = repository.getAllNotes()
            _viewState.value = notes
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
            getNotes()
        }
    }
}