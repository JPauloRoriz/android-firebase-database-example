package br.com.jpstudent.appmessage.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.usecase.*

class HomeViewModel(
    private val context : Context,
    private val deletePostUseCase: DeletePostUseCase,
    private val validationOwnerPost: ValidationOwnerPostUseCase,
    private val addPostUseCase: AddPostUseCase,
    private val getPostsUseCase: GetPostsUseCase,
    private val user: User
) : ViewModel() {
    val nameUserLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Post>>()
    val alertDialogDeletePostLiveData = MutableLiveData<Post>()
    val deletePostLiveData = MutableLiveData<String>()

    init {
        nameUserLiveData.value = user.name.split(" ")[0]
        getPosts()

    }

    private fun getPosts() {
        getPostsUseCase.getPosts(
            object : OnResultListner<List<Post>> {
                override fun onSuccess(result: List<Post>) {
                    postsLiveData.value = result
                }

                override fun onError(exception: Exception) {

                }

            }
        )
    }

    fun addPost(text: String) {
        addPostUseCase.addPost(
            user,
            text,
            object : OnResultListner<Void?> {
                override fun onSuccess(result: Void?) {
                    getPosts()
                }

                override fun onError(exception: Exception) {

                }


            }
        )
    }

    fun clickMessage(post: Post) {
        validationOwnerPost.invoke(user,
            post,
            object : OnResultListner<Post>{
                override fun onSuccess(result: Post) {
                    alertDialogDeletePostLiveData.value = result
                }

                override fun onError(exception: Exception) {

                }

            }
        )
    }

    fun deletePost(post: Post) {
        deletePostUseCase.invoke(
            post,
            object : OnResultListner<Void?>{
                override fun onSuccess(result: Void?) {
                    deletePostLiveData.value = context.getString(R.string.post_delete_success)
                }

                override fun onError(exception: Exception) {
                    deletePostLiveData.value = context.getString(R.string.message_nodeleted)
                }

            }
        )
    }





}