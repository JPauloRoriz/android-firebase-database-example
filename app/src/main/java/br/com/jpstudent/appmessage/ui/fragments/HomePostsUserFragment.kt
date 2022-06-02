package br.com.jpstudent.appmessage.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.jpstudent.appmessage.databinding.FragmentHomeBinding
import br.com.jpstudent.appmessage.databinding.FragmentHomePostsUserBinding
import br.com.jpstudent.appmessage.ui.adapter.ProfileUserAdapter
import br.com.jpstudent.appmessage.viewmodel.HomePostsUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomePostsUserFragment : Fragment(){
    private lateinit var binding: FragmentHomePostsUserBinding
    private val idUser by lazy { arguments?.getString(ID_USER) as String }
    private val adapter by lazy { ProfileUserAdapter() }
    private val viewModel: HomePostsUserViewModel by viewModel() {
        parametersOf(idUser)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePostsUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPosts.adapter = adapter

        setUpObservers()

    }

    private fun setUpObservers(){
        viewModel.nameUserLiveData.observe(viewLifecycleOwner){
            binding.imgProfile.textNameUser = it
        }

        viewModel.postsByUserLiveData.observe(viewLifecycleOwner) {
            adapter.refreshList(it)
        }
    }



    companion object {
        const val ID_USER: String = "user"
    }

}