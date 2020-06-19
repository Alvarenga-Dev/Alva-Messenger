package com.alvarengadev.alvamessenger.data.domain

class Friend {
    var id: String = ""
    var name: String = ""
    var email: String = ""

    constructor()

    constructor(id: String, name: String, email: String) {
        this.id = id
        this.name = name
        this.email = email
    }

    constructor(name: String) {
        this.name = name
    }
}
