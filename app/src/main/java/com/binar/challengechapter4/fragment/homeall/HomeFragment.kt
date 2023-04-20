package com.binar.challengechapter4.fragment.homeall

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.challengechapter4.R
import com.binar.challengechapter4.database.DataBaseNote
import com.binar.challengechapter4.database.NoteEntity
import com.binar.challengechapter4.databinding.FragmentHomeBinding
import com.binar.challengechapter4.fragment.noteall.NoteAdapter
import com.binar.challengechapter4.fragment.noteall.NoteVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var sharedPref : SharedPreferences
    var databNote : DataBaseNote? = null
    lateinit var noteAdapt : NoteAdapter
    lateinit var viewMN : NoteVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("DATAREGIS", Context.MODE_PRIVATE)
        _binding?.txtWelcomeUsername?.text = "Welcome,  " + sharedPref.getString("nama", "fullname")

        databNote = DataBaseNote.getInstance(requireContext())

        layoutViewManag()

        viewMN = ViewModelProvider(this).get(NoteVM::class.java)

        viewMN.getAllNoteObservers().observe(viewLifecycleOwner, Observer {
            noteAdapt.setNotes(it as ArrayList<NoteEntity>)
        })

        binding.fabAddData.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        binding.txtLogout.setOnClickListener {
            val dataSharedPref = sharedPref.edit()
            dataSharedPref.clear()
            dataSharedPref.apply()
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_loginFragment)
        }


    }

    fun layoutViewManag(){
        noteAdapt = NoteAdapter(ArrayList())
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.VERTICAL, false)

        binding.notesRecyclerView.adapter = noteAdapt

    }


    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            var data = databNote?.noteDao()?.getDataNote()

            activity?.runOnUiThread{
                noteAdapt = NoteAdapter(data!!)
                binding.notesRecyclerView.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false)
                binding.notesRecyclerView.adapter = noteAdapt

            }

        }
    }

}