package br.com.jpstudent.appmessage.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.database.OnResultVoidListener
import br.com.jpstudent.appmessage.model.exception.UserExistingException
import br.com.jpstudent.appmessage.usecase.SaveUserUseCase
import br.com.jpstudent.appmessage.usecase.exceptions.EmptyFildException
import br.com.jpstudent.appmessage.usecase.exceptions.PasswordLenghtException
import br.com.jpstudent.appmessage.usecase.exceptions.PasswordNotConfirmException

class RegisterViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val context: Context
) : ViewModel() {

    val errorRegisterLiveData = MutableLiveData<String>()
    val successRegisterLiveData = MutableLiveData<String>()

    fun tapOnRegisterUser(name: String, login: String, password: String, confirmPassword: String) {
        saveUserUseCase.invoke(
            name,
            login,
            password,
            confirmPassword,
            object : OnResultVoidListener {
                override fun onSuccess() {
                    successRegisterLiveData.value = context.getString(R.string.register_success)
                }

                override fun onError(exception: Exception) {
                    when (exception) {
                        is EmptyFildException,
                        is PasswordLenghtException,
                        is PasswordNotConfirmException -> {
                            errorRegisterLiveData.value = exception.message
                        }
                        is UserExistingException -> {
                            errorRegisterLiveData.value =
                                context.getString(R.string.email_already)
                        }
                        else -> {
                            errorRegisterLiveData.value =
                                context.getString(R.string.register_not_success)
                        }

                    }
                }
            }
        )
    }
}