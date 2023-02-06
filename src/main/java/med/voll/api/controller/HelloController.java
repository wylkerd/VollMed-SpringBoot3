package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Utilizando annotations do próprio Spring MVC
// Para que o Spring reconheça a classe como um Controller, ela deve ser anotada com @Controller ou com @RestController.
@RestController
@RequestMapping("/hello") // URL que esta controller irá responder
public class HelloController {

    @GetMapping
    public String olaMundo() {
        return "Hello World with Spring MVC, by Wylkerd Silva!";
    }
}
