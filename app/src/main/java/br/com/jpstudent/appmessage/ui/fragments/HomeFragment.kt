package br.com.jpstudent.appmessage.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.databinding.FragmentHomeBinding
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.ui.adapter.ProfileUserAdapter
import br.com.jpstudent.appmessage.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val user by lazy { arguments?.getSerializable(USER) as User }
    private val adapter by lazy { ProfileUserAdapter() }
    private val viewModel: HomeViewModel by viewModel {
        parametersOf(user)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        binding.rvPosts.adapter = adapter


    }

    private fun setupListeners() {
        binding.inputLayoutPost.setOnIconClickListener {
            viewModel.addPost(it)
        }

        adapter.clickText = {
            viewModel.clickMessage(it)
        }

        adapter.clickProfile = { user ->
            val bundle = bundleOf(HomePostsUserFragment.ID_USER to user)
            findNavController().navigate(R.id.action_homeFragment_to_homePostsUserFragment, bundle)
        }
    }

    private fun setupObservers() {
        viewModel.nameUserLiveData.observe(viewLifecycleOwner) {
            binding.imgProfile.textNameUser = it
        }

        viewModel.postsLiveData.observe(viewLifecycleOwner) {
            adapter.refreshList(it)
            binding.rvPosts.scrollToPosition(it.lastIndex)
        }

        viewModel.alertDialogDeletePostLiveData.observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.atention))
                .setMessage(getString(R.string.delete_post))
                .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                    viewModel.deletePost(it)
                    viewModel.deletePostLiveData.observe(viewLifecycleOwner) { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton(getString(R.string.not)) { dialog, witch ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }


    }

    companion object {
        const val USER: String = "user"
    }


}