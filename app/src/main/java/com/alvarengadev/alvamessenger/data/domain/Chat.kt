package com.alvarengadev.alvamessenger.data.domain

class Chat {

    var idUser: String = ""
    var name: String = ""
    var message: String = ""

    constructor()

    constructor(idUser: String, name: String, message: String) {
        this.idUser = idUser
        this.name = name
        this.message = message
    }

}