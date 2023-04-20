package com.binar.challengechapter4.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.binar.challengechapter4.R
import com.binar.challengechapter4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireContext().getSharedPreferences("DATAREGIS", Context.MODE_PRIVATE)


        binding.btnRegister.setOnClickListener {
            val fullname = binding.etFullnameRegister.text.toString()
            val username = binding.etUsernameRegister.text.toString()
            val password = binding.etPasswordRegister.text.toString()
            val konfirmPass = binding.etKonfirmPasswordRegister.text.toString()

            val registrasi = sharedPref.edit()
            registrasi.putString("nama", fullname)
            registrasi.putString("username", username)
            registrasi.putString("password", password)
            registrasi.apply()

            if(fullname.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && konfirmPass.isNotEmpty()){
                if(password == konfirmPass){
                    registrasi.apply()
                    Toast.makeText(context,"Registrasi Anda berhasil !", Toast.LENGTH_LONG).show()
                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                }else{
                    Toast.makeText(context, "Password tidak matching!", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(context, "input masih kosong", Toast.LENGTH_LONG).show()
            }

        }
    }


}