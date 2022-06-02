package br.com.jpstudent.appmessage.model.repository

import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.database.PostFirebase
import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User

class PostRepository(
    private val postFirebase : PostFirebase
) {
    fun getPosts(listener: OnResultListner<List<Post>>) {
        postFirebase.getPosts(listener)
    }

    fun addPost(user : User, text: String, listener: OnResultListner<Void?>) {
        postFirebase.addPost(user, text, listener)
    }

    fun deletePost(post: Post, listener: OnResultListner<Void?>) {
        postFirebase.deletePost(post, listener)
    }

    fun getPostsByUser(idUser: String, listener: OnResultListner<List<Post>>) {
        postFirebase.getPostsByUser(idUser, listener)
    }
}