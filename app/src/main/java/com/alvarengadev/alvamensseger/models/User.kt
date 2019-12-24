package com.alvarengadev.alvamensseger.models

import com.google.firebase.database.Exclude

class User {

    var id:String = ""
    var firstName:String = ""
    var lastName:String = ""
    var email:String = ""
    var password:String = ""

    constructor()
    constructor(firstName:String, lastName:String, email:String, password:String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

}