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

    override fun map(t: TopicoRequest): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor)
        )
    }

}
