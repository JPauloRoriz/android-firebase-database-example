package br.com.jpstudent.appmessage.usecase

import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.model.repository.PostRepository

class ShowPostsProfileUseCase
    (
    private val repository: PostRepository
) {
    fun invoke(idUser: String, listener: OnResultListner<List<Post>>) {
        repository.getPostsByUser(idUser, listener)
    }
}