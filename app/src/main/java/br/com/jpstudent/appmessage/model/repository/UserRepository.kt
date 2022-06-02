package br.com.jpstudent.appmessage.model.repository

import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.database.OnResultVoidListener
import br.com.jpstudent.appmessage.model.database.UserFirebase
import br.com.jpstudent.appmessage.model.entity.User

class UserRepository(
    private val userFirebase: UserFirebase
) {


    fun doLogin(login: String, password: String, listener: OnResultListner<User>) {
        userFirebase.doLogin(login, password, listener)
    }

    fun addUser(
        name: String,
        login: String,
        password: String,
        listener: OnResultVoidListener
    ) {
        userFirebase.saveNewUser(
            name = name,
            login = login,
            password = password,
            listener = listener)
    }

    fun getUserByEmail(login: String, listener : OnResultListner<User?>) {
        userFirebase.getUserByEmail(login, listener)
    }
}