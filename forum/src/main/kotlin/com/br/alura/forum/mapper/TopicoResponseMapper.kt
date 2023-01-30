package com.br.alura.forum.mapper

import com.br.alura.forum.dto.TopicoResponse
import com.br.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoResponseMapper: Mapper<Topico, TopicoResponse> {

    override fun map(t: Topico): TopicoResponse {

        return TopicoResponse(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            status = t.status
        )
    }
}