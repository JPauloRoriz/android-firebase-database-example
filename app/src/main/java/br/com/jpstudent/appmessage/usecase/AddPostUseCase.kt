package br.com.jpstudent.appmessage.usecase

import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.model.repository.PostRepository

class AddPostUseCase(
    private val repository: PostRepository
) {
    fun addPost(user : User, text : String, listener : OnResultListner<Void?>){
        repository.addPost(user, text, listener )

    }

}