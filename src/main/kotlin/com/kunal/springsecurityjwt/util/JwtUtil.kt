package com.kunal.springsecurityjwt.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
class JwtUtil {

    private val SECRET_KEY = "secret"

    fun extractUsername(token:String):String {
        return (Claims::getSubject)(extractAllClaims(token))
    }
    fun extractExpiration(token:String):Date {
        return (Claims::getExpiration)(extractAllClaims(token))
    }
    private fun extractAllClaims(token: String):Claims {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).body
    }
    private fun isTokenExpired(token: String):Boolean {
        return extractExpiration(token).before(Date())
    }
    fun generateToken(userDetails: UserDetails):String {
        val claims = HashMap<String,JvmType.Object>()
        return createToken(claims,userDetails.username)
    }
    private fun createToken(claims: Map<String, JvmType.Object>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact()
    }
    fun validateToken(token:String,userDetails:UserDetails):Boolean {
        val username = extractUsername(token)
        return (username.equals(userDetails.username)) && !isTokenExpired(token)
    }
}