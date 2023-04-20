package com.binar.challengechapter4.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.binar.challengechapter4.R
import com.binar.challengechapter4.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var sharedPref : SharedPreferences
    lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("DATAREGIS", Context.MODE_PRIVATE)
        binding.textBpa.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }

        binding.btnLogin.setOnClickListener {
            var getDataUser = sharedPref.getString("username", "")
            var getDataPass = sharedPref.getString("password", "")
            var username = binding.etUsernameLogin.text.toString()
            var password = binding.etPasswordLogin.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Username dan Password harus diisi !", Toast.LENGTH_SHORT).show()
            } else {
                if (username == getDataUser && password == getDataPass) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "username dan Pasword anda salah, Coba ulangi !", Toast.LENGTH_SHORT).show()
                }
            }



        }
    }


}