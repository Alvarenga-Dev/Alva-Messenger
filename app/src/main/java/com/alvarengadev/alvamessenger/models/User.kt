package com.alvarengadev.alvamessenger.models

import com.google.firebase.database.Exclude

class User {

    @get:Exclude
    var id: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    @get:Exclude
    var password: String = ""

    constructor()
    constructor(firstName: String, lastName: String, email: String, password: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }



}