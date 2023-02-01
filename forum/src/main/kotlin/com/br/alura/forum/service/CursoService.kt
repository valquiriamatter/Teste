package com.br.alura.forum.service

import com.br.alura.forum.model.Curso
import com.br.alura.forum.repository.CursoRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): Curso{
        //return repository.findById(id) as Curso
        return repository.getOne(id)
    }



}
