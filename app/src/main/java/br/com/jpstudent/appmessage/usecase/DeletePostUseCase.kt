package br.com.jpstudent.appmessage.usecase

import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.repository.PostRepository

class DeletePostUseCase(
    private val repository: PostRepository
) {
    fun invoke(post: Post, listener: OnResultListner<Void?>) {
        repository.deletePost(post, listener)
    }
}