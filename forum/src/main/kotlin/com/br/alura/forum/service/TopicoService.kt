package com.br.alura.forum.service

import com.br.alura.forum.dto.TopicoPorCategoriaDTO
import com.br.alura.forum.dto.TopicoRequest
import com.br.alura.forum.dto.TopicoResponse
import com.br.alura.forum.dto.TopicoUpdateRequest
import com.br.alura.forum.exceptions.NotFoundException
import com.br.alura.forum.mapper.TopicoRequestMapper
import com.br.alura.forum.mapper.TopicoResponseMapper
import com.br.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val topicoResponseMapper: TopicoResponseMapper,
    private val topicoRequestMapper: TopicoRequestMapper,
    private val notFoundMessage: String = "Tópico não encontrado!",
    //private val em: EntityManager  se precisar usar o EntityManager
) {

    fun listar(nomeCurso: String?, @PageableDefault(size = 5,
        sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable): Page<TopicoResponse> {

        val topicos =
            if (nomeCurso == null) repository.findAll(paginacao)
            else repository.findByCursoNome(nomeCurso, paginacao)

        return topicos.map { t ->
            topicoResponseMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): TopicoResponse {
        val topico = repository.findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        return topicoResponseMapper.map(topico)
    }

//    fun buscarPorId(id: Long): TopicoResponse { id == lambada ?
//        val topico = topicos.find { t -> t.id == id } ?: NotFoundException("Tópico não econtrado")
//        return topicoResponseMapper.map(topico as Topico)
//    }

//    fun getArticle(id: String): ArticleModel {
//        return articles.find { articleModel -> articleModel.id == id }
//            ?: throw ArticleNotFoundException("Article not found")
//    }


    fun cadastrar(topicoRequest: TopicoRequest): TopicoResponse {
        val topico = topicoRequestMapper.map(topicoRequest)
        repository.save(topico)
        return topicoResponseMapper.map(topico)
    }

    fun atualizar(topicoUpdateRequest: TopicoUpdateRequest): TopicoResponse {

        val topico = repository.findById(topicoUpdateRequest.id).orElseThrow { NotFoundException(notFoundMessage) }

        topico.titulo = topicoUpdateRequest.titulo
        topico.mensagem = topicoUpdateRequest.mensagem

        return topicoResponseMapper.map(topico)
    }

//    fun deletar(id: Long) {
//        val topico = topicos.stream().filter { it.id == id }.findFirst()
//            .orElseThrow { NotFoundException(notFoundMessage) }
//        topicos = topicos.minus(topico)
//    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDTO> {
        return repository.relatorio()
    }

}