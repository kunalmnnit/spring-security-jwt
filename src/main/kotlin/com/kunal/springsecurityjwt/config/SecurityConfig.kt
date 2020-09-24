package com.kunal.springsecurityjwt.config

import com.kunal.springsecurityjwt.filters.JwtRequestFilter
import com.kunal.springsecurityjwt.service.MyUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var service: MyUserDetailService

    @Autowired
    private lateinit var jwtRequestFilter: JwtRequestFilter

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(service)
    }

    override fun configure(http: HttpSecurity?){
        http!!.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun passwordEncoder():PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}