package com.binar.challengechapter4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binar.challengechapter4.R
import com.binar.challengechapter4.databinding.FragmentDeleteBinding


class DeleteFragment : Fragment() {

    lateinit var binding : FragmentDeleteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

}