package com.example.news.views.fragments.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentLoginBinding
import com.example.news.viewmodels.AuthViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.LogInButton.setOnClickListener {
            loginUser()
        }

        binding.textview2.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    private fun loginUser() {
        val email = binding.textInputEditText1.text.toString().trim()
        val password = binding.textInputEditText2.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(email, password)
        }

    }


    private fun observeData() {

        viewModel.isLogin.observe(viewLifecycleOwner) {
            if (it) {
                //Toast.makeText(context, "${binding.textInputEditText1.text.toString().trim()}", Toast.LENGTH_LONG).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                Toast.makeText(context, "Login Faild", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
         //   if (it) binding.progressBar2.visible() else binding.progressBar2.gone()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
