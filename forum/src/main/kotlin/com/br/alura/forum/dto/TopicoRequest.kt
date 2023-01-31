package com.br.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoRequest(
    @field:NotEmpty(message = "O campo mensagem não pode ser vazio!")
    @Size(min = 5, max = 100, message = "Título deve ter entre 5 e 100 caracteres.") val titulo: String,
    @field:NotEmpty(message = "O campo não pode ser vazio!") val mensagem: String,
    @field:NotNull(message = "O idCurso não pode ser nulo!") val idCurso: Long,
    @field:NotNull(message = "O idAutor não pode ser nulo!") val idAutor: Long
)
