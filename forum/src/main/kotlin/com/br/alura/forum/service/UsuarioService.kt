package com.br.alura.forum.service

import com.br.alura.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(var autores: List<Usuario>) {

    init {

        val autor1 = Usuario(id = 1, nome = "Valquíria", email = "...@gmail.com")
        autores = arrayListOf(autor1)

    }

    fun buscarPorId(id: Long): Usuario{
        return autores.stream().filter { it.id == id }.findFirst().get()
    }
}
