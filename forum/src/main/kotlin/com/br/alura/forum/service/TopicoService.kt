package com.br.alura.forum.service

import com.br.alura.forum.dto.TopicoRequest
import com.br.alura.forum.dto.TopicoResponse
import com.br.alura.forum.dto.TopicoUpdateRequest
import com.br.alura.forum.mapper.TopicoRequestMapper
import com.br.alura.forum.mapper.TopicoResponseMapper
import com.br.alura.forum.model.Topico
import com.br.alura.forum.exceptions.NotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoResponseMapper: TopicoResponseMapper,
    private val topicoRequestMapper: TopicoRequestMapper,
    private val notFoundMessage: String = "Tópico não encontrado!"
) {

    fun listar(): List<TopicoResponse> {
        return topicos.stream().map { t ->
            topicoResponseMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoResponse {
        val topico = topicos.stream().filter { it.id == id }.findFirst()
            .orElseThrow { NotFoundException(notFoundMessage) }
        return topicoResponseMapper.map(topico)
    }

//    fun buscarPorId(id: Long): TopicoResponse {
//        val topico = topicos.find { t -> t.id == id } ?: NotFoundException("Tópico não econtrado")
//        return topicoResponseMapper.map(topico as Topico)
//    }

//    fun getArticle(id: String): ArticleModel {
//        return articles.find { articleModel -> articleModel.id == id }
//            ?: throw ArticleNotFoundException("Article not found")
//    }


    fun cadastrar(topicoRequest: TopicoRequest): TopicoResponse {
        val topico = topicoRequestMapper.map(topicoRequest)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
        return topicoResponseMapper.map(topico)
    }

    fun atualizar(topicoUpdateRequest: TopicoUpdateRequest): TopicoResponse {

        try {

            val topico = topicos.stream().filter { it.id == topicoUpdateRequest.id }.findFirst().get()

            val topicoAtualizado = topico.copy(
                titulo = topicoUpdateRequest.titulo,
                mensagem = topicoUpdateRequest.mensagem
            )

            topicos = topicos.minus(topico).plus(
                topicoAtualizado
//            Topico(
//                id = topicoUpdateRequest.id,
//                titulo = topicoUpdateRequest.titulo,
//                mensagem = topicoUpdateRequest.mensagem,
//                autor = topico.autor,
//                curso = topico.curso,
//                respostas = topico.respostas,
//                status = topico.
//            )
            )
            return topicoResponseMapper.map(topicoAtualizado)

        } catch (e: Exception) {
            throw NotFoundException(notFoundMessage)
        }
    }

//    fun deletar(id: Long) {
//        val topico = topicos.stream().filter { it.id == id }.findFirst()
//            .orElseThrow { NotFoundException(notFoundMessage) }
//        topicos = topicos.minus(topico)
//    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{ NotFoundException(notFoundMessage) }
        topicos = topicos.minus(topico)
    }

}