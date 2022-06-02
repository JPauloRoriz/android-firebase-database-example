package br.com.jpstudent.appmessage.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.databinding.FragmentLoginBinding
import br.com.jpstudent.appmessage.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.tapOnLogin(
                binding.edtLogin.text.toString(),
                binding.edtPassword.text.toString()
            )

        }

        binding.btnRegister.setOnClickListener {
            viewModel.tapOnRegister()
        }
    }

    private fun setupObservers() {
        viewModel.toRegisterLiveData.observe(viewLifecycleOwner) {

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        viewModel.successLoginLiveData.observe(viewLifecycleOwner) { user ->
            binding.edtLogin.text?.clear()
            binding.edtPassword.text?.clear()
            val bundle = bundleOf(HomeFragment.USER to user)
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
        }

        viewModel.errorLoginLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.showLoadingLiveData.observe(viewLifecycleOwner){
            binding.pbLoading.isVisible = it
        }
    }

}