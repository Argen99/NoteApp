package com.geektech.homework71.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.geektech.homework71.NoteAdapter
import com.geektech.homework71.databinding.ActivityMainBinding
import com.geektech.homework71.domain.model.Note
import dagger.hilt.android.AndroidEntryPoint

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

        viewModel.liveData.observe(this) {
            adapter = NoteAdapter(it)
            binding.rvNotes.adapter = adapter
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