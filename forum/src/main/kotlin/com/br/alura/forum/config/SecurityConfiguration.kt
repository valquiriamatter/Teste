package com.br.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter(){

    override fun configure(http: HttpSecurity?) {
        http?.
        authorizeRequests()?.
            antMatchers("/topicos")?.hasAuthority("LEITURA_ESCRITA")?. //para acessar precisa ter a authority
        anyRequest()?.
        authenticated()?. //qualquer requesição deve estar autenticado
        and()?.
        sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?. //não guarda estado
        and()?.formLogin()?.disable()?. //desativa janela de login
        httpBasic() //forma de autenticacao
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder()) //fazer o encode da senha
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

}