package br.com.jpstudent.appmessage.usecase

import android.content.Context
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.model.repository.UserRepository
import br.com.jpstudent.appmessage.usecase.exceptions.EmptyFildException

class DoLoginUseCase(
    private val context : Context,
    private val repository: UserRepository
) {
    fun validationLogin(login : String, password: String, listener : OnResultListner<User>){
        if(login.isEmpty() || password.isEmpty()){
            listener.onError(EmptyFildException(context.getString(R.string.empty_fild)))
        }
        else {
            repository.doLogin(login, password, listener)
        }
    }

}