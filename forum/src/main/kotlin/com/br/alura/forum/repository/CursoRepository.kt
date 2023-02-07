package com.br.alura.forum.repository

import com.br.alura.forum.model.Curso
import com.br.alura.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {




}