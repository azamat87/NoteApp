package kz.azamat.notelessondb.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data_source.Note
import com.example.noteapp.repository.NotesSQLiteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import java.util.*

class NotesDetailViewModel(private val noteId: Long?, private val repository: NotesSQLiteRepository) : ViewModel() {

    private val _viewState = MutableStateFlow<Note?>(null)
    val viewState: Flow<Note> get() = _viewState.filterNotNull()


    init {
        noteId?.let {
            viewModelScope.launch {
                val note = repository.get(noteId)
                _viewState.value = note
            }
        }
    }

    fun onScreenClose(title: String, note: String) {
        viewModelScope.launch {
            val currentNote = _viewState.value
            if (currentNote == null) {
                if (title.isNotEmpty() || note.isNotEmpty())
                    repository.create(title, note)
            } else {
                repository.update(currentNote.copy(title = title, text = note, date = Date()))
            }
        }
    }

    companion object {
        fun Factory(noteId: Long?) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NotesDetailViewModel(
                    noteId = noteId,
                    repository = NotesSQLiteRepository
                ) as T
            }
        }
    }
}