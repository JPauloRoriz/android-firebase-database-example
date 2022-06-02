package br.com.jpstudent.appmessage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.usecase.ShowPostsProfileUseCase

class HomePostsUserViewModel(
    private val showPostsProfileUseCase: ShowPostsProfileUseCase,
    private val idUser: String
) : ViewModel() {
    val nameUserLiveData = MutableLiveData<String>()
    val postsByUserLiveData = MutableLiveData<List<Post>>()

    init {
        inflateMessage(idUser)
    }

    private fun inflateMessage(idUser: String) {
        showPostsProfileUseCase.invoke(
            idUser,
            object : OnResultListner<List<Post>> {
                override fun onSuccess(result: List<Post>) {
                    nameUserLiveData.value = result.first().nameUser
                    postsByUserLiveData.value = result
                }

                override fun onError(exception: Exception) {

                }
            }
        )
    }


}