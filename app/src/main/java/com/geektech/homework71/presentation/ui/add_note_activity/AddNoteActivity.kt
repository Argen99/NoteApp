package com.geektech.homework71.presentation.ui.add_note_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.homework71.databinding.ActivityAddNoteBinding
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.extensions.showToast

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickers()
    }
    private fun initClickers() {
        binding.btnEnter.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            when {
                title.isEmpty() -> {
                    showToast("Enter title")
                }
                description.isEmpty() -> {
                    showToast("Enter description")
                }
                else -> {
                    Intent().apply {
                        putExtra(noteKey, Note(title = title, description =  description))
                        setResult(RESULT_OK, this)
                        finish()
                    }
                }
            }
        }
    }
    companion object {
        const val noteKey = "tKey"
    }
}