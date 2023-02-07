package med.voll.api.controller;

import med.voll.api.medico.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Annotation para que o Spring carregue esta Classe como uma controller, ao iniciar o projeto
@RestController
@RequestMapping("medicos") // nomeando a URL
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        // atalho escrito 'sout'
        System.out.println(dados);

    }
}

// @RequestBody indica que o parâmetro virá do corpo da requisição, o Body no formato JSON.

// ALT + ENTER -> Create record 'DadosCadastroMedico'. Record é como uma Classe java, porém imutável, proporcionando um código menos verboso
// (sem necessidade de Getters e Setters, por exemplo) se comparado a uma Classe, é um recurso das versões mais recentes.
