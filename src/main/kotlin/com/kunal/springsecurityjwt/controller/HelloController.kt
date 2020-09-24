package com.kunal.springsecurityjwt.controller

import com.kunal.springsecurityjwt.model.AuthenticationRequest
import com.kunal.springsecurityjwt.model.AuthenticationResponse
import com.kunal.springsecurityjwt.service.MyUserDetailService
import com.kunal.springsecurityjwt.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception


@RestController
class HelloController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userDetailsService: MyUserDetailService

    @Autowired
    private lateinit var jwtTokenUtil:JwtUtil

    @GetMapping("/hello")
    fun hello():String {
        return "Hello,World!"
    }
    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.username,authenticationRequest.password))

        } catch (e:BadCredentialsException) {
            throw Exception("Incorrect username or password")
        }
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val jwt = jwtTokenUtil.generateToken(userDetails)
        println("idhar aa rha hai ${jwt}")
        return ResponseEntity.ok(AuthenticationResponse(jwt))
    }
}