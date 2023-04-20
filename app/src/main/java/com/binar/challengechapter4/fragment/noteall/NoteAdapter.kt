package com.binar.challengechapter4.fragment.noteall

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.binar.challengechapter4.R
import com.binar.challengechapter4.database.DataBaseNote
import com.binar.challengechapter4.database.NoteEntity
import com.binar.challengechapter4.databinding.NoteListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter(var listNote : List<NoteEntity>): RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    var dbNote : DataBaseNote ? = null

    class ViewHolder(var binding : NoteListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itemData : NoteEntity){
            binding.dataNoted = itemData
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        var view = NoteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
       holder.binding.dataNoted = listNote[position]

        holder.binding.ivEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("EDITDATA", listNote[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment, bundle)
        }

        holder.binding.ivDelete.setOnClickListener {
            dbNote = DataBaseNote.getInstance(it.context)
            Toast.makeText(it.context, "Note Berhasil dihapus :)", Toast.LENGTH_SHORT).show()
            GlobalScope.async {
                NoteVM(Application()).deleteNote(listNote[position])
                dbNote?.noteDao()?.deleteNote(listNote[position])
                val nav = Navigation.findNavController(it)
                nav.run{
                    navigate(R.id.homeFragment)
                }
            }

        }


    }

    override fun getItemCount(): Int = listNote.size

    fun setNotes(notes: ArrayList<NoteEntity>) {
        this.listNote = notes
    }

}