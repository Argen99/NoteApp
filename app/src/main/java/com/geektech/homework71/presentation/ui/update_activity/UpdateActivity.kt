package com.geektech.homework71.presentation.ui.update_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.homework71.databinding.ActivityUpdateBinding
import com.geektech.homework71.domain.model.Note
import com.geektech.homework71.extensions.showToast
import com.geektech.homework71.presentation.ui.add_note_activity.AddNoteActivity
import com.geektech.homework71.presentation.ui.main_activity.MainActivity

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val note = intent.getSerializableExtra(MainActivity.noteKey) as Note
        binding.etUpdateTitle.setText(note.title)
        binding.etUpdateDescription.setText(note.description)

        binding.btnUpdate.setOnClickListener {
            val title = binding.etUpdateTitle.text.toString()
            val description = binding.etUpdateDescription.text.toString()
            when {
                title.isEmpty() -> {
                    showToast("Enter title")
                }
                description.isEmpty() -> {
                    showToast("Enter description")
                }
                else -> {
                    Intent().apply {
                        putExtra(updateNoteKey, Note(id = note.id, title = title, description =  description))
                        setResult(RESULT_OK, this)
                        finish()
                    }
                }
            }
        }
    }
    companion object {
        const val updateNoteKey = "updateNoteKey"
    }
}