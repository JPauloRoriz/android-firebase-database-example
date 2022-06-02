package br.com.jpstudent.appmessage.usecase

import android.content.Context
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.usecase.exceptions.NoOwnerPostException

class ValidationOwnerPostUseCase(
    private val context: Context
) {
    fun invoke(user: User, post: Post, listener: OnResultListner<Post>) {
        if (user.id == post.idUser) {
            listener.onSuccess(post)
        } else {
            listener.onError(NoOwnerPostException(context.getString(R.string.exception_owner_post)))
        }
    }

}