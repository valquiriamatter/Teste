package com.br.alura.forum.dto

import com.br.alura.forum.model.StatusTopico
import java.time.LocalDateTime

class TopicoResponse(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)