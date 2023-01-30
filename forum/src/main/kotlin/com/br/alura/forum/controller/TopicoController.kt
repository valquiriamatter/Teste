package com.br.alura.forum.controller

import com.br.alura.forum.dto.TopicoRequest
import com.br.alura.forum.dto.TopicoResponse
import com.br.alura.forum.model.Topico
import com.br.alura.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private var service: TopicoService) {

    @GetMapping
    fun listar(): List<TopicoResponse>{
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long):TopicoResponse{
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody topico: TopicoRequest){
        service.cadastrar(topico)
    }

}