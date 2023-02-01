package com.br.alura.forum.service

import com.br.alura.forum.model.Usuario
import com.br.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario{
        return repository.getOne(id)
    }
}
