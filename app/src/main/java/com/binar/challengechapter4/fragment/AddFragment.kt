package com.binar.challengechapter4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.binar.challengechapter4.R
import com.binar.challengechapter4.database.DataBaseNote
import com.binar.challengechapter4.database.NoteEntity
import com.binar.challengechapter4.databinding.FragmentAddBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class AddFragment : Fragment() {
    lateinit var binding : FragmentAddBinding
    var dbNote : DataBaseNote? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbNote = DataBaseNote.getInstance(requireContext())


        binding.btnAddInput.setOnClickListener {
            GlobalScope.async {
                var title = binding.etAddJudul.text.toString()
                var note = binding.etAddDescription.text.toString()

                dbNote!!.noteDao().insertNote(NoteEntity(0,title, note))
            }
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)

        }

    }


}