package med.voll.api.controller;

import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Annotation para que o Spring carregue esta Classe como uma controller, ao iniciar o projeto
@RestController
@RequestMapping("medicos") // nomeando a URL
public class MedicoController {

    // Autowired: Annotation para Injeção de Dependencia, para criar este objeto e passar ao controller automaticamente
    @Autowired
    private MedicoRepository respository;  // Injetando o Repository como sendo um atriburo do Controller, denominado repository com tipo MedicoRepository

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        respository.save(new Medico(dados));
    }
}

// @RequestBody indica que o parâmetro virá do corpo da requisição, o Body no formato JSON.

// ALT + ENTER -> Create record 'DadosCadastroMedico'. Record é como uma Classe java, porém imutável, proporcionando um código menos verboso
// (sem necessidade de Getters, equals, hashCode e toString por exemplo) se comparado a uma Classe, é um recurso das versões mais recentes.
