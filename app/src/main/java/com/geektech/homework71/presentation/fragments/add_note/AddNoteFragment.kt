package com.geektech.homework71.presentation.fragments.add_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.homework71.databinding.FragmentAddNoteBinding
import com.geektech.homework71.domain.model.room.Note
import com.geektech.homework71.extensions.showToast

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickers()
    }
    private fun initClickers() {
        binding.btnEnter.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            when {
                title.isEmpty() -> {
                    requireContext().showToast("Enter title")
                }
                description.isEmpty() -> {
                    requireContext().showToast("Enter description")
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putSerializable("key", Note(title = title, description = description))
                    parentFragmentManager.setFragmentResult("1",bundle)
                    findNavController().navigateUp()
                }
            }
        }
    }
}

