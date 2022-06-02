package br.com.jpstudent.appmessage.model.database

interface OnResultVoidListener {
    fun onSuccess()
    fun onError(exception: Exception)
}