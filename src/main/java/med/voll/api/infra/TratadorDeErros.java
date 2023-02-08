package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Annotation para indicar ao Spring que esta Classe que tratará os erros, pois é do tipo RestControllerAdvice
@RestControllerAdvice
public class TratadorDeErros {

    // Indicação de qual erro este método vai tratar, neste caso Entity not found, passando de 500 para 404 Not Found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

}
