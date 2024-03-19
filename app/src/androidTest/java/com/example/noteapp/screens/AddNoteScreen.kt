package com.example.noteapp.screens

import com.kaspersky.kaspresso.screens.KScreen
import com.example.noteapp.R
import io.github.kakaocup.kakao.edit.KEditText
import kz.azamat.notelessondb.detail.NotesDetailFragment

object AddNoteScreen: KScreen<AddNoteScreen>() {

    private val titleEd = KEditText { withId(R.id.title_et)}
    private val noteEd = KEditText { withId(R.id.note_et)}

    fun inputNote(title: String, note: String) {
        titleEd.typeText(title)
        noteEd.typeText(note)
        pressBack()
        pressBack()
    }


    override val layoutId: Int = R.layout.fragment_notes_detail

    override val viewClass: Class<*> = NotesDetailFragment::class.java
}