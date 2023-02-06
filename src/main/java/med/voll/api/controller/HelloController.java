package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Utilizando annotations do proprio Spring MVC
@RestController
@RequestMapping("/hello") // URL que esta controller irá responder
public class HelloController {

    /*
    * Método público para realização de GET, conforme a annotation indicando o protocolo http correspondente
    * Tipo de retorno do método é String
    */
    @GetMapping
    public String olaMundo() {
        return "Hello World with Spring MVC, by Wylkerd Silva!";
    }
}
