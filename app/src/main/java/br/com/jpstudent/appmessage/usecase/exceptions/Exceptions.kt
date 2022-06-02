package br.com.jpstudent.appmessage.usecase.exceptions

class EmptyFildException(private val errorMessage : String) : Exception(errorMessage)
class PasswordLenghtException(private val errorMessage : String) : Exception(errorMessage)
class PasswordNotConfirmException(private val errorMessage : String) : Exception(errorMessage)
class NoOwnerPostException(private val errorMessage : String) : Exception(errorMessage)