package br.com.jpstudent.appmessage.model.database

import br.com.jpstudent.appmessage.model.entity.Post
import br.com.jpstudent.appmessage.model.entity.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PostFirebase(
    private val database: FirebaseDatabase
) {
    private val postFirebase by lazy { database.getReference(POSTS_TABLE) }


    fun addPost(user: User, message: String, listener: OnResultListner<Void?>) {
        postFirebase.push().key?.let { id ->
            postFirebase.child(id).setValue(
                Post(
                    id = id,
                    idUser = user.id ?: "",
                    text = message,
                    nameUser = user.name
                )).addOnSuccessListener {
                listener.onSuccess(it)
            }.addOnFailureListener {
                listener.onError(it)
            }
        }
    }

    fun getPosts(listener: OnResultListner<List<Post>>) {
        postFirebase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listPosts = snapshot.children.mapNotNull {
                    it.getValue(Post::class.java)
                }
                listener.onSuccess(listPosts)
            }

            override fun onCancelled(error: DatabaseError) {
                listener.onError(error.toException())
            }
        })
    }

    fun getPostsByUser(idUser: String, listener: OnResultListner<List<Post>>) {
        postFirebase.orderByChild("idUser").equalTo(idUser)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listPosts = snapshot.children.mapNotNull {
                        it.getValue(Post::class.java)
                    }
                    listener.onSuccess(listPosts)
                }

                override fun onCancelled(error: DatabaseError) {
                    listener.onError(error.toException())
                }

            })
    }

    fun deletePost(post: Post, listener: OnResultListner<Void?>) {
        postFirebase.child(post.id.toString()).removeValue { error, ref ->
            if (error == null) {
                listener.onSuccess(null)
            } else {
                listener.onError(error.toException())
            }
        }
    }

    companion object {
        private const val POSTS_TABLE = "Posts"
    }
}