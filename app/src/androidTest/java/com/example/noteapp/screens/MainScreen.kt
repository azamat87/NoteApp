package com.example.noteapp.screens

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.noteapp.NotesListFragment
import com.kaspersky.kaspresso.screens.KScreen
import com.example.noteapp.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object MainScreen: KScreen<MainScreen>() {

    private val  buttonAddNote = KButton{ withId(R.id.add_button) }

    val noteRv = KRecyclerView(
        builder = { withId(R.id.notes_rv) },
        itemTypeBuilder = { itemType(::NoteItem) }
    )

    fun clickAdd() {
        buttonAddNote.click()
    }

    override val layoutId: Int = R.layout.fragment_notes_list

    override val viewClass: Class<*> = NotesListFragment::class.java

    class NoteItem(matcher: Matcher<View>) : KRecyclerItem<NoteItem>(matcher) {

        val deleteIcon = KImageView(matcher) { withId(R.id.delete_icon)}
        val tvTitle = KTextView(matcher) { withId(R.id.title_tv) }

    }

}