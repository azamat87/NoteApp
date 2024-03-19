package com.example.noteapp.screens

import androidx.test.ext.junit.rules.activityScenarioRule
import com.example.noteapp.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test


class NoteTest: TestCase() {

    @get:Rule
    val activity = activityScenarioRule<MainActivity>()

    @Test
    fun noteTest() {

        MainScreen.clickAdd()

        AddNoteScreen {
            inputNote("Title first", "Note text")
        }

        MainScreen {

            noteRv {
                childAt<MainScreen.NoteItem>(0) {
                    tvTitle.hasAnyText()
                    deleteIcon.click()
                }
            }
        }

    }

}