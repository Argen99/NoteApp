package com.geektech.homework71

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.homework71.databinding.ItemNotesBinding
import com.geektech.homework71.domain.model.Note

class NoteAdapter(private val list: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class NoteViewHolder(private val binding: ItemNotesBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvDescription.text = note.description
        }


    }


}