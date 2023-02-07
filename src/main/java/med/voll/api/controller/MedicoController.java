package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos") // nomeando a URL
public class MedicoController {

    @Autowired // Annotation para Injeção de Dependencia, para criar este objeto e passar ao controller automaticamente
    private MedicoRepository respository;

    @PostMapping
    @Transactional // Como este é um método de escrita no BD, é necessário ter uma transação ativa com o Banco de Dados
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        respository.save(new Medico(dados)); // Pressionar ALT + Enter, para criar construtor de Medico que recebe DadosCadastroMedico
    }

    @GetMapping
    public List<DadosListagemMedico> listar() {
        // transformando um lista de Medicos em uma lista do Dto DadosListagemMedico
        return respository.findAll().stream().map(DadosListagemMedico::new).toList(); // Lembrar de criar construtor no DTO
    }

}

// ------------ COMENTÁRIOS ----------------- //
// @RequestBody indica que o parâmetro virá do corpo da requisição, o Body no formato JSON.

// ALT + ENTER -> Create record 'DadosCadastroMedico'. Record é como uma Classe java, porém imutável, proporcionando um código menos verboso
// (sem necessidade de Getters, equals, hashCode e toString por exemplo) se comparado a uma Classe, é um recurso das versões mais recentes.

// Annotation RestController para que o Spring carregue esta Classe como uma controller, ao iniciar o projeto

// Injetando o Repository como sendo um atriburo do Controller, denominado repository com tipo MedicoRepository