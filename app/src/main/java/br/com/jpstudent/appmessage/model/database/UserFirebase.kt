package br.com.jpstudent.appmessage.model.database

import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.model.exception.LoginInvalidException
import br.com.jpstudent.appmessage.model.exception.UserExistingException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserFirebase(
    private val database: FirebaseDatabase
) {
    private val usersFirebase by lazy { database.getReference(USERS_TABLE) }

    fun saveNewUser(name: String, login: String, password: String, listener: OnResultVoidListener) {
        getUserByEmail(login, object : OnResultListner<User?> {
            override fun onSuccess(result: User?) {
                if (result != null) {
                    listener.onError(UserExistingException())
                } else {
                    saveUser(name, login, password, listener)
                }
            }

            override fun onError(exception: Exception) {
                listener.onError(exception)
            }
        })
    }

    fun saveUser(name: String, login: String, password: String, listener: OnResultVoidListener) {
        usersFirebase.push().key?.let { id ->
            usersFirebase.child(id)
                .setValue(User(id, name, login, password))
                .addOnSuccessListener {
                    listener.onSuccess()
                }
                .addOnFailureListener {
                    listener.onError(it)
                }
        }
    }

    fun getUserByEmail(email: String, listner: OnResultListner<User?>) {
        usersFirebase
            .orderByChild("email").equalTo(email)
            .limitToFirst(1)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    listner.onSuccess(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    listner.onError(error.toException())
                }
            })
    }

    fun doLogin(login: String, password: String, listner: OnResultListner<User>) {
        usersFirebase
            .orderByChild("email").equalTo(login)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.children.map {
                        it.getValue(User::class.java)
                    }.toList().firstOrNull { it?.password == password }
                    when (user) {
                        null -> listner.onError(LoginInvalidException())
                        else -> listner.onSuccess(user)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    listner.onError(error.toException())
                }
            })
    }


    companion object {
        private const val USERS_TABLE = "users"
    }
}