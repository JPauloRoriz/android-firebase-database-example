package br.com.jpstudent.appmessage.model.database

interface OnResultListner<T> {
    fun onSuccess(result: T)
    fun onError(exception: Exception)
}