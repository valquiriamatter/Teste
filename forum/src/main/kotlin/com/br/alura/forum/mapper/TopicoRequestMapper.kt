package com.br.alura.forum.mapper

import com.br.alura.forum.dto.TopicoRequest
import com.br.alura.forum.model.Topico
import com.br.alura.forum.service.CursoService
import com.br.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoRequestMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) : Mapper<TopicoRequest, Topico> {

    override fun map(topico: TopicoRequest): Topico {
        return Topico(
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            curso = cursoService.buscarPorId(topico.idCurso),
            autor = usuarioService.buscarPorId(topico.idAutor)
        )
    }

}
