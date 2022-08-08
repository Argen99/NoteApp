package com.geektech.homework71.presentation.ui.main_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.homework71.databinding.ItemNotesBinding
import com.geektech.homework71.domain.model.Note

class NoteAdapter(private val list: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var clickListener: ItemClickListener

    fun setItemClickListener(clickListener: ItemClickListener){
        this.clickListener = clickListener
    }

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

            itemView.setOnClickListener {
                clickListener.onItemClick(list[adapterPosition])
                notifyItemRemoved(adapterPosition)
            }
            itemView.setOnLongClickListener {
                clickListener.onLingClickListener(list[adapterPosition])
                return@setOnLongClickListener true
            }
        }


    }

    interface ItemClickListener {
        fun onItemClick(note: Note)
        fun onLingClickListener(note: Note)
    }
}