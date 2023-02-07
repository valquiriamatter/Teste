package com.br.alura.forum.controller

import com.br.alura.forum.dto.TopicoRequest
import com.br.alura.forum.dto.TopicoResponse
import com.br.alura.forum.dto.TopicoUpdateRequest
import com.br.alura.forum.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(@RequestParam(required = false) nomeCurso: String?): List<TopicoResponse>{
        return service.listar(nomeCurso)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long):TopicoResponse{
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid topicoRequest: TopicoRequest, uriBuilder: UriComponentsBuilder):
            ResponseEntity<TopicoResponse> {
        val topico = service.cadastrar(topicoRequest)
        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()
        return ResponseEntity.created(uri).body(topico)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid topico: TopicoUpdateRequest): ResponseEntity<TopicoResponse>{
        val topicoResponse = service.atualizar(topico)
        return ResponseEntity.ok(topicoResponse)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long){
        service.deletar(id)
    }

}