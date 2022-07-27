package com.geektech.homework71.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.geektech.homework71.databinding.ActivityMainBinding
import com.geektech.homework71.domain.model.Note

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ArrayAdapter<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        var i = 0
        binding.btnAddNote.setOnClickListener {
            i++
            viewModel.addNote(Note(i.toString(), i.toString()))
        }
        binding.btnDelete.setOnClickListener {
            if (!adapter.isEmpty) {
                viewModel.deleteNote(adapter.count - 1)
                i--
            }
        }
        viewModel.liveData.observe(this) {
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, it)
            binding.lvNotes.adapter = adapter
        }
    }
}