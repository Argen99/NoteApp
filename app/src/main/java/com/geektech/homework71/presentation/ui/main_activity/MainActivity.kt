package com.geektech.homework71.presentation.ui.main_activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.geektech.homework71.R
import com.geektech.homework71.core.BaseActivity
import com.geektech.homework71.core.UIState
import com.geektech.homework71.databinding.ActivityMainBinding
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.extensions.invisible
import com.geektech.homework71.extensions.showToast
import com.geektech.homework71.extensions.visible
import com.geektech.homework71.presentation.ui.add_note_activity.AddNoteActivity
import com.geektech.homework71.presentation.ui.update_activity.UpdateActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), NoteAdapter.ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteAdapter
    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.addNote(result.data?.getSerializableExtra(AddNoteActivity.noteKey) as Note)
            }
        }
    private val intentLauncherUpdate =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.updateNote(result.data?.getSerializableExtra(UpdateActivity.updateNoteKey) as Note)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getAllNotes()
        initClickers()

        viewModel.addNotesState.subscribe {
            when (it) {
                is UIState.Error -> {
                    showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    showToast("Add Note")
                    binding.progressBar.invisible()
                    if (it.data) {
                        viewModel.getAllNotes()
                    }
                }
            }
        }


        viewModel.noteState.subscribe {
            when (it) {
                is UIState.Error -> {
                    showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    showToast("Get All Notes")
                    binding.progressBar.invisible()
                    adapter = NoteAdapter(it.data)
                    binding.rvNotes.adapter = adapter
                    adapter.setItemClickListener(this)
                }
            }


        }
        viewModel.deleteNotesState.subscribe {
            when (it) {
                is UIState.Error -> {
                    showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    showToast("Delete Note")
                    binding.progressBar.invisible()
                    if (it.data) {
                        viewModel.getAllNotes()
                    }
                }
            }

        }
        viewModel.updateNoteState.subscribe {
            when (it) {
                is UIState.Error -> {
                    showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    showToast("Update Note")
                    binding.progressBar.invisible()
                    if (it.data) {
                        viewModel.getAllNotes()
                    }
                }
            }
        }
    }

    private fun initClickers() {
        binding.fubAdd.setOnClickListener {
            intentLauncher.launch(Intent(this, AddNoteActivity::class.java))
        }
    }

    override fun onItemClick(note: Note) {
        intentLauncherUpdate.launch(Intent(this, UpdateActivity::class.java).apply{
            putExtra(noteKey, note)
        })
    }

    override fun onLingClickListener(note: Note) {
        viewModel.deleteNote(note)
    }

    companion object {
        const val noteKey = "noteKey"
    }
}