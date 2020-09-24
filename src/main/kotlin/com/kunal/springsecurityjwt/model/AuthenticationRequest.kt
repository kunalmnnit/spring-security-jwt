package com.kunal.springsecurityjwt.model

data class AuthenticationRequest(
        var username:String,
        var password:String
)