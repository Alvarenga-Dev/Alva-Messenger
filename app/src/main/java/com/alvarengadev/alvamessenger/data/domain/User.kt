package com.alvarengadev.alvamessenger.data.domain

import com.google.firebase.database.Exclude

class User {

    @get:Exclude
    var id: String = ""
    var name: String = ""
    var email: String = ""

    @get:Exclude
    var password: String = ""

    constructor()

    constructor(name: String, email: String, password: String) {
        this.name = name
        this.email = email
        this.password = password
    }

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }
}
