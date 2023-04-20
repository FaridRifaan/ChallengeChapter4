package com.binar.challengechapter4.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.challengechapter4.R
import com.binar.challengechapter4.database.DataBaseNote
import com.binar.challengechapter4.database.NoteEntity
import com.binar.challengechapter4.databinding.FragmentEditBinding
import com.binar.challengechapter4.fragment.noteall.NoteVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class EditFragment : Fragment() {
    lateinit var binding : FragmentEditBinding
    var dbNote : DataBaseNote? = null
    lateinit var  viewMN : NoteVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbNote = DataBaseNote.getInstance(requireContext())
        viewMN = ViewModelProvider(this).get(NoteVM::class.java)

        var getNoteData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("EDITDATA", NoteEntity::class.java)
        } else {
            arguments?.getParcelable("EDITDATA")
        }
        if (getNoteData != null) {
            binding.etEditTitle.setText(getNoteData.title)
        }
        if (getNoteData != null) {
            binding.etEditDescription.setText(getNoteData.description)
        }
        binding.btnUpdate.setOnClickListener {
            editNote()
        }

    }
    fun editNote() {
        var title = binding.etEditTitle.text.toString()
        var note = binding.etEditDescription.text.toString()

        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(NoteEntity(id, title, note))

        }
        Toast.makeText(requireContext(), "Data note berhasil di edit", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_editFragment_to_homeFragment)
    }


}