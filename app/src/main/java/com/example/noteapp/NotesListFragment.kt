package com.example.noteapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.noteapp.data_source.Note
import com.example.noteapp.databinding.FragmentNotesListBinding
import kotlinx.coroutines.launch


class NotesListFragment : Fragment() {

    private val viewModel: NotesListViewModel by viewModels()
    private var _binding: FragmentNotesListBinding? = null
    private val adapter = NotesListAdapter(object : NotesListAdapter.NoteItemListener {
        override fun onNoteClick(note: Note) {
            openDetails(note.id)
        }

        override fun deleteNote(note: Note) {
            viewModel.delete(note)
        }
    })

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesRv.adapter = adapter
        binding.addButton.setOnClickListener { openDetails(null) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { notes ->
                    adapter.submitList(notes)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun openDetails(noteId: Long?) {
        val args = noteId?.let { bundleOf("noteId" to noteId) }
        findNavController().navigate(R.id.action_NotesListFragment_to_NotesDetailFragment, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}