package com.geektech.homework71.presentation.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.homework71.databinding.FragmentUpdateBinding
import com.geektech.homework71.domain.model.room.Note
import com.geektech.homework71.extensions.showToast
import com.geektech.homework71.presentation.fragments.main.MainFragment

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val note = arguments?.getSerializable(MainFragment.keyNote) as Note
        binding.etTitleUpdate.setText(note.title)
        binding.etDescriptionUpdate.setText(note.description)

        binding.btnUpdate.setOnClickListener {
            val title = binding.etTitleUpdate.text.toString()
            val description = binding.etDescriptionUpdate.text.toString()
            when {
                title.isEmpty() -> {
                    requireContext().showToast("Enter title")
                }
                description.isEmpty() -> {
                    requireContext().showToast("Enter description")
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putSerializable(
                        updateNoteKey,
                        Note(id = note.id, title = title, description = description)
                    )
                    parentFragmentManager.setFragmentResult("2", bundle)
                    findNavController().navigateUp()

                }
            }
        }
    }

    companion object {
        const val updateNoteKey = "updateNoteKey"
    }
}

