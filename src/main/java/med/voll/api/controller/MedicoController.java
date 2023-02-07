package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Annotation para que o Spring carregue esta Classe como uma controller, ao iniciar o projeto
@RestController
@RequestMapping("medicos") // nomeando a URL
public class MedicoController {

    // @RequestBody indica que o parâmetro virá do corpo da requisição, o Body no formato JSON
    @PostMapping
    public void cadastrar(@RequestBody String json) {
        System.out.println(json); // atalho escrito 'sout'
        
    }
}
