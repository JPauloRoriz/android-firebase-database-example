package br.com.jpstudent.appmessage.model.entity

data class Post(
    var id : String? = null,
    val idUser: String = "",
    val nameUser : String = "",
    val text: String = ""
)
