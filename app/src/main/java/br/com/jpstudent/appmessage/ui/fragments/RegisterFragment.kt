package br.com.jpstudent.appmessage.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.databinding.FragmentRegisterBinding
import br.com.jpstudent.appmessage.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private val viewModel by viewModel<RegisterViewModel>()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListners()
        setupObservers()
    }

    private fun setupListners() {
        binding.btnConfirm.setOnClickListener {
            viewModel.tapOnRegisterUser(
                binding.edtInputName.text.toString(),
                binding.edtInputLogin.text.toString(),
                binding.edtInputPassword.text.toString(),
                binding.edtInputConfirmPassword.text.toString()
            )
        }
    }

    private fun setupObservers() {
        viewModel.successRegisterLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        viewModel.errorRegisterLiveData.observe(viewLifecycleOwner) {
            binding.edtInputPassword.text?.clear()
            binding.edtInputConfirmPassword.text?.clear()
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }


}


