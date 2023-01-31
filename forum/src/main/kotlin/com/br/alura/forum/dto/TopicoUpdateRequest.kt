package com.br.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoUpdateRequest(
    @field:NotNull val id: Long,
    @field:NotEmpty @Size(min = 5, max = 100) val titulo: String,
    @field:NotEmpty val mensagem: String
)