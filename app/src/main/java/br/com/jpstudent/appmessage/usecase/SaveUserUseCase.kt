package br.com.jpstudent.appmessage.usecase

import android.content.Context
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.database.OnResultVoidListener
import br.com.jpstudent.appmessage.model.repository.UserRepository
import br.com.jpstudent.appmessage.usecase.exceptions.EmptyFildException
import br.com.jpstudent.appmessage.usecase.exceptions.PasswordLenghtException
import br.com.jpstudent.appmessage.usecase.exceptions.PasswordNotConfirmException

class SaveUserUseCase(
    private val repository: UserRepository,
    private val context: Context
) {

    fun invoke(
        name: String,
        login: String,
        password: String,
        confirmPassword: String,
        listner: OnResultVoidListener
    ) {
        if (name.isEmpty() || login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            listner.onError(EmptyFildException(context.getString(R.string.empty_fild)))
            return
        }

        if (password.length < 4) {
            listner.onError(PasswordLenghtException(context.getString(R.string.password_lenght)))
            return
        }
        if (password != confirmPassword) {
            listner.onError(PasswordNotConfirmException(context.getString(R.string.password_not_confirm)))
            return
        }
        repository.addUser(name, login, password, listner)
    }
}