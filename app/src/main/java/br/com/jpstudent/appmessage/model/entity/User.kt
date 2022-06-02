package br.com.jpstudent.appmessage.model.entity

import java.io.Serializable

data class User(
    var id: String? = null,
    val name : String = "",
    val email : String = "",
    val password : String = ""
) : Serializable
