package com.br.alura.forum.dto

data class TopicoRequest(
    val titulo: String,
    val mensagem: String,
    val idCurso: Long,
    val idAutor: Long
)
