package com.alvarengadev.alvamessenger.models

class Message {
    var id: String = ""
    var message: String = ""

    constructor()
    constructor(id: String, message: String) {
        this.id = id
        this.message = message
    }
}