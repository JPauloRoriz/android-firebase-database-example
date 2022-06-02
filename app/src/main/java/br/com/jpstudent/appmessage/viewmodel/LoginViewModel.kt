package br.com.jpstudent.appmessage.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.jpstudent.appmessage.R
import br.com.jpstudent.appmessage.model.database.OnResultListner
import br.com.jpstudent.appmessage.model.entity.User
import br.com.jpstudent.appmessage.model.exception.LoginInvalidException
import br.com.jpstudent.appmessage.usecase.DoLoginUseCase
import br.com.jpstudent.appmessage.viewmodel.util.SingleLiveEvent

class LoginViewModel(
    private val context: Context,
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    var toRegisterLiveData = SingleLiveEvent<Unit?>()
    val successLoginLiveData = SingleLiveEvent<User>()
    val errorLoginLiveData = SingleLiveEvent<String>()
    val showLoadingLiveData = SingleLiveEvent<Boolean>()


    fun tapOnLogin(login: String, password: String) {
        showLoadingLiveData.value = true
        doLoginUseCase.validationLogin(
            login,
            password,
            object : OnResultListner<User> {
                override fun onSuccess(result: User) {
                    showLoadingLiveData.value = false
                    successLoginLiveData.value = result
                }

                override fun onError(exception: Exception) {
                    showLoadingLiveData.value = false
                    when (exception) {
                        is LoginInvalidException -> {
                            errorLoginLiveData.value = context.getString(R.string.login_incomplete)
                        }
                        else -> errorLoginLiveData.value = exception.message
                    }
                }
            }
        )
    }

    fun tapOnRegister() {
        toRegisterLiveData.call()
    }
}