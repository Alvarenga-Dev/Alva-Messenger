package com.alvarengadev.alvamessenger.data.domain

class Friend {
    var id: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""

    constructor()

    constructor(id: String, firstName: String, lastName: String, email: String) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }
}
