package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        List<Topico> topicos = null;
        if(nomeCurso == null){
            topicos = topicoRepository.findAll();
        }else{
            topicos = topicoRepository.findByCurso_Nome(nomeCurso);
        }

        return TopicoDto.converter(topicos);
    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm topicoForm){
        Topico topico = topicoForm.converter(this.cursoRepository);
        topicoRepository.save(topico);
    }
}
