package br.com.jpstudent.appmessage.usecase

import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.repository.PostRepository

class GetPostsUseCase(
    private val repository: PostRepository
) {
    fun getPosts(listener: OnResultListner<List<Post>>) {
        repository.getPosts(listener)
    }


}