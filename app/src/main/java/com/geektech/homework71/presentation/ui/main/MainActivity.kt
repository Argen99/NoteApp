package com.geektech.homework71.presentation.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.geektech.homework71.NoteAdapter
import com.geektech.homework71.core.UIState
import com.geektech.homework71.databinding.ActivityMainBinding
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.extensions.gone
import com.geektech.homework71.extensions.invisible
import com.geektech.homework71.extensions.showToast
import com.geektech.homework71.extensions.visible
import com.geektech.homework71.presentation.ui.add_note.AddNoteActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteAdapter
    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK){
                viewModel.addNote(result.data?.getSerializableExtra(AddNoteActivity.noteKey) as Note)
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initClickers()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addNotesState.collect {
                    when (it) {
                        is UIState.Error -> {
                            showToast(it.error)
                        }
                        is UIState.Loading -> {
                            binding.progressBar.visible()
                        }
                        is UIState.Success -> {
                            showToast("Kruto")
                            binding.progressBar.invisible()
                            if (it.data){
                                viewModel.getAllNotes()
                            }
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.noteState.collect {
                    when (it) {
                        is UIState.Error -> {
                            showToast(it.error)
                        }
                        is UIState.Loading -> {
                            binding.progressBar.visible()
                        }
                        is UIState.Success -> {
                            showToast("Kruto")
                            binding.progressBar.invisible()
                            adapter = NoteAdapter(it.data)
                            binding.rvNotes.adapter = adapter
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.deleteNotesState.collect {
                    when (it) {
                        is UIState.Error -> {
                            showToast(it.error)
                        }
                        is UIState.Loading -> {
                            binding.progressBar.visible()
                        }
                        is UIState.Success -> {
                            showToast("Kruto")
                            binding.progressBar.invisible()
                            if (it.data){
                                viewModel.getAllNotes()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initClickers() {
        binding.fubDelete.setOnClickListener {
            viewModel.deleteNote()
        }

        binding.fubAdd.setOnClickListener {
            intentLauncher.launch(Intent(this, AddNoteActivity::class.java))
        }
    }
}