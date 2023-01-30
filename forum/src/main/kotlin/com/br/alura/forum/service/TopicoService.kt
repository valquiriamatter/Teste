package com.br.alura.forum.service

import com.br.alura.forum.dto.TopicoRequest
import com.br.alura.forum.dto.TopicoResponse
import com.br.alura.forum.mapper.TopicoRequestMapper
import com.br.alura.forum.mapper.TopicoResponseMapper
import com.br.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoResponseMapper: TopicoResponseMapper,
    private val topicoRequestMapper: TopicoRequestMapper
) {

    fun listar(): List<TopicoResponse>{
        return topicos.stream().map {
                t -> topicoResponseMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoResponse {
        val topico = topicos.stream().filter { it.id == id }.findFirst().get()
        return topicoResponseMapper.map(topico)
    }

    fun cadastrar(topicoRequest: TopicoRequest) {
        val topico = topicoRequestMapper.map(topicoRequest)
        topico.id = topicos.size.toLong() +1
        topicos = topicos.plus(topico)
    }

}